package dam_A51706.finalproject.ui.states

import dam_A51706.finalproject.data.DifficultyLevel
import dam_A51706.finalproject.data.Step
import java.util.Date

data class GoalState (
    val title: String = "",
    val description: String = "",
    val deadline: Date = Date(),
    val reward: String = "",
    val difficulty: DifficultyLevel = DifficultyLevel.EASY,
    val steps: List<Step> = listOf(),
    )