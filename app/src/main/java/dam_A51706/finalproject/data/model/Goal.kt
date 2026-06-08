package dam_A51706.finalproject.data.model

import java.util.Date

data class Goal (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var deadline: Date = Date(),
    var reward: String = "",
    var difficulty: DifficultyLevel = DifficultyLevel.EASY,
    var steps: List<Step> = listOf(),
    var currentStep: Int = 0,
    var isComplete: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var completedAt: Long? = null
)

enum class DifficultyLevel (){
    VERY_EASY,
    EASY,
    MEDIUM,
    HARD,
    VERY_HARD
}