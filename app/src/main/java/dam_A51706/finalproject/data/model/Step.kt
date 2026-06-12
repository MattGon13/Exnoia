package dam_A51706.finalproject.data.model

import com.google.firebase.firestore.PropertyName
import java.util.UUID

data class Step (
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    @get:PropertyName("isCompleted") @set:PropertyName("isCompleted")
    var isCompleted: Boolean = false,
    var completedAt: Long? = null
)