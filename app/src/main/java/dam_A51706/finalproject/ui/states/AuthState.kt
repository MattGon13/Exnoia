package dam_A51706.finalproject.ui.states

import com.google.firebase.auth.FirebaseUser

/**
 * Indicates the state of the user authentication
 */
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: FirebaseUser) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
    object LoggedOut : AuthUiState()
}

/**
 * Keeps the state of the authentication forms (login and register)
 */
data class AuthFormState(
    val email: String = "",
    val password: String = ""
)