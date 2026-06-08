package dam_A51706.finalproject.data.model

import java.util.UUID

data class Step (
    var id: String = UUID.randomUUID().toString(),
    var title: String,
    var isCompleted: Boolean = false,
    var completedAt: Long? = null
)