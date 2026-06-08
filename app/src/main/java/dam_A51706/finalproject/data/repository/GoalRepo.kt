package dam_A51706.finalproject.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dam_A51706.finalproject.data.model.Goal
import dam_A51706.finalproject.data.model.Step
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class GoalRepo {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    /**
     * Get the goals collection of a user from firestore
     */
    private fun getGoalsCollection() = firestore.collection("users").document(getUserId()).collection("goals")

    /**
     * Get current logged in user id
     */
    private fun getUserId(): String {
        return auth.currentUser?.uid ?: throw Exception("User not logged in")
    }

    /**
     * Add new goal to firestore as a document in the goals collection
     */
    suspend fun addGoal(goal: Goal): Result<String> {
        return try {
            //DocumentReference pointing to a new document with an auto-generated ID within this collection
            val ref = getGoalsCollection().document()
            //Update created goal with new id
            val goalWithId = goal.copy(id = ref.id)

            ref.set(goalWithId).await()
            Result.success(ref.id)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Put current user's goals ordered from youngest to oldest in a cold flow asynchronously
     */
    fun getGoals(): Flow<List<Goal>> = callbackFlow {
        val listener = getGoalsCollection()
            .orderBy("createdAt", Query.Direction.DESCENDING)
            //Returns the current goals and gets called any time that data changes
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener //local return to the caller of the lambda
                }
                if (snapshot != null) {
                    //Get all goal documents transformed in a goal object that aren't null
                    val goals = snapshot.documents.mapNotNull { it.toObject(Goal::class.java) }
                    //Send goals to flow
                    trySend(goals)
                }
            }
        awaitClose { listener.remove() }
    }

    /**
     * Update the goal information in the firestore collection
     */
    suspend fun updateGoal(goal: Goal): Result<Unit> {
        return try {
            getGoalsCollection().document(goal.id).set(goal).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Delete the goal in the firestore collection
     */
    suspend fun deleteGoal(goalId: String): Result<Unit> {
        return try {
            getGoalsCollection().document(goalId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Update a given goal's steps in the firestore and the information if a goal is completed
     * or not and when it was completed based on the current completed steps. A goal is only
     * considered completed when all the steps are marked as complete.
     */
    suspend fun updateSteps(goalId: String, steps: List<Step>): Result<Unit> {
        return try {
            //Check if goal is completed
            val isComplete = steps.isNotEmpty() && steps.all { it.isCompleted }
            val completedAt = if (isComplete) System.currentTimeMillis() else null

            //Update goal's steps and info about being complete
            getGoalsCollection().document(goalId).update(
                mapOf(
                    "steps" to steps,
                    "isComplete" to isComplete,
                    "completedAt" to completedAt
                )
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}