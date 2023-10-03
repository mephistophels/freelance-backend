package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import java.time.LocalDateTime

class OrderResponse(
    id: Long,
    createdAt: LocalDateTime,
    val title: String,
    val content: String,
    val price: Int,
    val status: OrderStatus,
    val customer: UserResponse,
    val executor: UserResponse? = null
) : AbstractCreatedAtResponse(id, createdAt)