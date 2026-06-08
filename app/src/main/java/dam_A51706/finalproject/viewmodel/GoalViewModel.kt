package dam_A51706.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dam_A51706.finalproject.data.model.DifficultyLevel
import dam_A51706.finalproject.data.model.Goal
import dam_A51706.finalproject.data.model.Step
import dam_A51706.finalproject.data.repository.GoalRepo
import dam_A51706.finalproject.ui.states.GoalFormState
import dam_A51706.finalproject.ui.states.GoalListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import kotlin.collections.component1

class GoalViewModel (application: Application): AndroidViewModel(application) {

    private val goalRepo = GoalRepo()

    // State holder for user's goal list state that is observed by the goals screen (GoalsPortrait)
    private val _uiState = MutableStateFlow(GoalListUiState(isLoading = true))
    val uiState: StateFlow<GoalListUiState> = _uiState.asStateFlow()

    // State holder for the create goal forms
    private val _formState = MutableStateFlow(GoalFormState())
    val formState: StateFlow<GoalFormState> = _formState.asStateFlow()

    // State holder for the search query used to search for a goal in the list
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // State holder for the current list of goals
    private val _goals = MutableStateFlow<List<Goal>>(emptyList())

    // State holder for the current list of goals filtered by the search query the user wrote
    val filteredGoals: StateFlow<List<Goal>> = combine(_goals, _searchQuery) { goals, query ->
        if (query.isBlank()) {
            goals
        } else {
            goals.filter { it.title.contains(query, ignoreCase = true) }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // State holder to indicate which step was selected by the user
    private val _selectedGoal = MutableStateFlow<Goal?>(null)
    val selectedGoal: StateFlow<Goal?> = _selectedGoal.asStateFlow()


    // State holders for the user stats
    val totalGoalsCount = combine(_goals) { (goals) -> goals.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalGoalsCompleted = combine(_goals) { (goals) -> goals.count { it.isComplete } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalStepsCount = combine(_goals) { (goals) -> goals.sumOf { it.steps.size } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalStepsCompleted = combine(_goals) { (goals) -> goals.sumOf { it.steps.count { step -> step.isCompleted } } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val goalsCompletedThisMonth = combine(_goals) { (goals) ->
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        goals.count { goal ->
            if (goal.isComplete && goal.completedAt != null) {
                val cal = Calendar.getInstance().apply { timeInMillis = goal.completedAt!! }
                cal.get(Calendar.MONTH) == currentMonth && cal.get(Calendar.YEAR) == currentYear
            } else false
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val stepsCompletedThisMonth = combine(_goals) { (goals) ->
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        goals.sumOf { goal ->
            goal.steps.count { step ->
                if (step.isCompleted && step.completedAt != null) {
                    val cal = Calendar.getInstance().apply { timeInMillis = step.completedAt!! }
                    cal.get(Calendar.MONTH) == currentMonth && cal.get(Calendar.YEAR) == currentYear
                } else false
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    /**
     * Load all user goals from firestore
     */
    fun loadGoals() {
        viewModelScope.launch {
            try {
                goalRepo.getGoals().collect { goals ->
                    _goals.value = goals
                    _uiState.value = GoalListUiState(goals = goals, isLoading = false)

                    // Update selected goal if it exists to refresh UI
                    _selectedGoal.value?.let { current ->
                        _selectedGoal.value = goals.find { it.id == current.id }
                    }
                }
            } catch (e: Exception) {
                _uiState.value = GoalListUiState(isLoading = false, error = e.message)
            }
        }
    }

    /**
     * Update the search query that is used to filter goals
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Update selected goal to the goal the user chose
     */
    fun selectGoal(goal: Goal?) {
        _selectedGoal.value = goal
    }

    /**
     * Initialize edit goal form with the goal's current information
     */
    fun initForm(goal: Goal?) {
        if (goal != null) {
            _formState.value = GoalFormState(
                goalId = goal.id,
                title = goal.title,
                description = goal.description,
                deadline = goal.deadline,
                reward = goal.reward,
                difficulty = goal.difficulty,
                isEditMode = true
            )
        } else {
            _formState.value = GoalFormState()
        }
    }

    /**
     * Update the create and edit goal forms
     */
    fun updateForm(
        title: String? = null,
        description: String? = null,
        deadline: Date? = null,
        reward: String? = null,
        difficulty: DifficultyLevel? = null
    ) {
        val current = _formState.value
        _formState.value = current.copy(
            title = title ?: current.title,
            description = description ?: current.description,
            deadline = deadline ?: current.deadline,
            reward = reward ?: current.reward,
            difficulty = difficulty ?: current.difficulty
        )
    }

    /**
     * Save information about the goal that was created/updated with the forms
     */
    fun saveGoal(onComplete: () -> Unit) {
        val state = _formState.value
        viewModelScope.launch {
            if (state.isEditMode && state.goalId != null) {
                // Find existing goal to preserve steps and metadata
                val existing = _goals.value.find { it.id == state.goalId } ?: return@launch
                val updated = existing.copy(
                    title = state.title,
                    description = state.description,
                    deadline = state.deadline,
                    reward = state.reward,
                    difficulty = state.difficulty
                )
                goalRepo.updateGoal(updated)
            } else {
                val newGoal = Goal(
                    title = state.title,
                    description = state.description,
                    deadline = state.deadline,
                    reward = state.reward,
                    difficulty = state.difficulty
                )
                goalRepo.addGoal(newGoal)
            }
            onComplete()
        }
    }

    /**
     * Delete goal from firebase
     */
    fun deleteGoal(goalId: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            goalRepo.deleteGoal(goalId)
            onComplete()
        }
    }

    /**
     * Add a new step to a goal to firebase
     */
    fun addStep(goalId: String, stepTitle: String) {
        val goal = _goals.value.find { it.id == goalId } ?: return
        val newStep = Step(title = stepTitle)
        val updatedSteps = goal.steps + newStep
        viewModelScope.launch {
            goalRepo.updateSteps(goalId, updatedSteps)
        }
    }

    /**
     * Update an existing goal's step in firebase
     */
    fun updateStep(goalId: String, stepId: String, newTitle: String) {
        val goal = _goals.value.find { it.id == goalId } ?: return
        val updatedSteps = goal.steps.map {
            if (it.id == stepId) it.copy(title = newTitle) else it
        }
        viewModelScope.launch {
            goalRepo.updateSteps(goalId, updatedSteps)
        }
    }

    /**
     * Delete existing step from goal from the firebase
     */
    fun deleteStep(goalId: String, stepId: String) {
        val goal = _goals.value.find { it.id == goalId } ?: return
        val updatedSteps = goal.steps.filter { it.id != stepId }
        viewModelScope.launch {
            goalRepo.updateSteps(goalId, updatedSteps)
        }
    }

    /**
     * Update completed state of a goal's step
     */
    fun toggleStepComplete(goalId: String, stepId: String, isCompleted: Boolean) {
        val goal = _goals.value.find { it.id == goalId } ?: return
        val updatedSteps = goal.steps.map {
            if (it.id == stepId) {
                it.copy(
                    isCompleted = isCompleted,
                    completedAt = if (isCompleted) System.currentTimeMillis() else null
                )
            } else it
        }
        viewModelScope.launch {
            goalRepo.updateSteps(goalId, updatedSteps)
        }
    }

}