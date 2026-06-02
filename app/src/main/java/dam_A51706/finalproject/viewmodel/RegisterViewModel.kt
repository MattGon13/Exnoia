package dam_A51706.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dam_A51706.finalproject.ui.states.LoginState
import dam_A51706.finalproject.ui.states.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel (application: Application): AndroidViewModel(application) {

    private val _registerState= MutableStateFlow(RegisterState())
    val weatherUIState: StateFlow<RegisterState> = _registerState.asStateFlow()


}