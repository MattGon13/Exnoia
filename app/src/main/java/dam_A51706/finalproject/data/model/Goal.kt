package dam_A51706.finalproject.data.model

import java.util.Date

data class Goal (
    var title: String,
    var description: String,
    var deadline: Date,
    var reward: String,
    var difficulty: DifficultyLevel,
    var steps: List<Step>,
    var currentStep: Int,
    var isComplete: Boolean = false
)

enum class DifficultyLevel (){
    VERY_EASY,
    EASY,
    MEDIUM,
    HARD,
    VERY_HARD
}