package dam_A51706.finalproject.ui.states

import dam_A51706.finalproject.data.model.DifficultyLevel
import dam_A51706.finalproject.data.model.Goal
import java.util.Date

/**
 * Keeps the state of the goal forms
 */
data class GoalFormState (
    val goalId: String? = null,
    val title: String = "",
    val description: String = "",
    val deadline: Date = Date(),
    val reward: String = "",
    val difficulty: DifficultyLevel = DifficultyLevel.EASY,
    val isEditMode: Boolean = false
)

/**
 * Keeps the state of the user's goals list
 */
data class GoalListUiState(
    val goals: List<Goal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)