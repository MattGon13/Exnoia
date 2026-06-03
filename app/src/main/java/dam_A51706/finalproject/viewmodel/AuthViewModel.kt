package dam_A51706.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dam_A51706.finalproject.data.repository.AuthRepo
import dam_A51706.finalproject.ui.states.AuthFormState
import dam_A51706.finalproject.ui.states.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel (application: Application): AndroidViewModel(application) {
    private val authRepo: AuthRepo = AuthRepo()

    // State holder for authentication state that is observed by de authentication UI
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    // State holder for the fields in authentication forms that is observed by de authentication UI
    private val _formState = MutableStateFlow(AuthFormState())
    val formState: StateFlow<AuthFormState> = _formState.asStateFlow()

    init {
        checkAuth()
    }

    /**
     * Check if user has logged in/registered or has no authentication
     */
    fun checkAuth() {
        val user = authRepo.currentUser()
        if (user != null) {
            _uiState.value = AuthUiState.Success(user)
        } else {
            _uiState.value = AuthUiState.Idle
        }
    }

    /**
     * Update value of email field in form state
     */
    fun updateEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    /**
     * Update value of password field in form state
     */
    fun updatePassword(password: String) {
        _formState.value = _formState.value.copy(password = password)
    }

    /**
     * Register new user with the values passed by the form's fields
     */
    fun register() {
        val email = _formState.value.email
        val password = _formState.value.password
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Email and password cannot be empty")
            return
        }

        _uiState.value = AuthUiState.Loading
        viewModelScope.launch {
            val result = authRepo.register(email, password)
            result.onSuccess { user ->
                _uiState.value = AuthUiState.Success(user)
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(error.message ?: "Registration failed")
            }
        }
    }

    /**
     * Log in existing user with the values passed by the form's fields
     */
    fun signIn() {
        val email = _formState.value.email
        val password = _formState.value.password
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Email and password cannot be empty")
            return
        }

        _uiState.value = AuthUiState.Loading
        viewModelScope.launch {
            val result = authRepo.login(email, password)
            result.onSuccess { user ->
                _uiState.value = AuthUiState.Success(user)
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(error.message ?: "Login failed")
            }
        }
    }

    /**
     * Log out existing users that previously logged in
     */
    fun signOut() {
        authRepo.signOut()
        _uiState.value = AuthUiState.LoggedOut
        _formState.value = AuthFormState() // clear form
    }

    /**
     * Reset authentication state to idle if there was an error
     */
    fun resetState() {
        if (_uiState.value is AuthUiState.Error) {
            _uiState.value = AuthUiState.Idle
        }
    }

}