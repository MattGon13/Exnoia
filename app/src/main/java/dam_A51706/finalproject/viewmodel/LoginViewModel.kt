package dam_A51706.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dam_A51706.finalproject.ui.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel (application: Application): AndroidViewModel(application) {

    private val _loginState= MutableStateFlow(LoginState())
    val weatherUIState: StateFlow<LoginState> = _loginState.asStateFlow()


}