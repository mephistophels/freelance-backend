package com.mephistophels.freelancing.model.request

import com.mephistophels.freelancing.database.entity.MarkRecipient

data class MarkRequest(
    val mark: Int,
    val message: String,
    val recipient: MarkRecipient,
    val customerId: Long,
    val executorId: Long,
    val orderId: Long,
)