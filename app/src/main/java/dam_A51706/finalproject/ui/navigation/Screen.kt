package dam_A51706.finalproject.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object Goals : Screen("goals")
    object GoalInfo : Screen("goal_info/{goalId}") {
        fun createRoute(goalId: String) = "goal_info/$goalId"
    }
    object CreateEditGoal : Screen("create_edit_goal?goalId={goalId}") {
        fun createRoute(goalId: String?) = goalId?.let { "create_edit_goal?goalId=$it" } ?: "create_edit_goal"
    }
}