package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import com.mephistophels.freelancing.model.response.common.AbstractResponse
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDateTime

class OrderResponse(
    id: Long,
    createdAt: LocalDateTime,
    val title: String,
    val body: String,
    val price: Int,
    val status: OrderStatus,
    val user: UserResponse
) : AbstractCreatedAtResponse(id, createdAt)