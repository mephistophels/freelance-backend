package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import com.mephistophels.freelancing.model.response.user.UserResponse
import java.time.LocalDateTime

class OrderExecutionRequestResponse(
    id: Long, createdAt: LocalDateTime,
    val content: String,
    val executor: UserResponse
) : AbstractCreatedAtResponse(id, createdAt)