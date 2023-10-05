package com.mephistophels.freelancing.model.request

import com.mephistophels.freelancing.database.entity.OrderStatus

data class StatusUpdateRequest(
    val orderId: Long,
    val status: OrderStatus,
)