package dam_A51706.finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dam_A51706.finalproject.ui.screens.CreateEditGoalPortrait
import dam_A51706.finalproject.ui.screens.GoalInfoPortrait
import dam_A51706.finalproject.ui.screens.GoalsPortrait
import dam_A51706.finalproject.ui.screens.LoginScreenPortrait
import dam_A51706.finalproject.ui.screens.MainScreenPortrait
import dam_A51706.finalproject.ui.screens.RegisterScreenPortrait
import dam_A51706.finalproject.viewmodel.AuthViewModel
import dam_A51706.finalproject.viewmodel.GoalViewModel


@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
) {
    val authViewModel: AuthViewModel = viewModel()
    val goalViewModel: GoalViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreenPortrait(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Login.route) { inclusive = false }
                    }
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreenPortrait(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
