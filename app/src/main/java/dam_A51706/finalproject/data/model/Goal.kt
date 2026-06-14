package dam_A51706.finalproject.data.model

import com.google.firebase.firestore.PropertyName
import dam_A51706.finalproject.R
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
    @get:PropertyName("isComplete") @set:PropertyName("isComplete")
    var isComplete: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var completedAt: Long? = null
)

enum class DifficultyLevel (var stringId: Int){
    VERY_EASY(R.string.very_easy),
    EASY(R.string.easy),
    MEDIUM(R.string.medium),
    HARD(R.string.hard),
    VERY_HARD(R.string.very_hard)
}