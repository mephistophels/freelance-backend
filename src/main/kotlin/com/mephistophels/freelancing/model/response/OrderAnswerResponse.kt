package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.database.entity.OrderAnswerStatus
import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import com.mephistophels.freelancing.model.response.user.UserResponse
import java.time.LocalDateTime

class OrderAnswerResponse(
    id: Long,
    createdAt: LocalDateTime,
    val content: String,
    val status: OrderAnswerStatus,
    val executor: UserResponse? = null
) : AbstractCreatedAtResponse(id, createdAt)