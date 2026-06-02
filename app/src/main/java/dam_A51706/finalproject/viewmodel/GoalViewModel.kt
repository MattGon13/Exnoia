package dam_A51706.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dam_A51706.finalproject.ui.states.GoalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GoalViewModel (application: Application): AndroidViewModel(application) {

    private val _goalState= MutableStateFlow(GoalState())
    val weatherUIState: StateFlow<GoalState> = _goalState.asStateFlow()


}