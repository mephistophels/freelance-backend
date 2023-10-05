package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import java.time.LocalDateTime

class BalanceOperationResponse(
    id: Long,
    createdAt: LocalDateTime,
    val price: Int,
    val order: OrderResponse? = null
): AbstractCreatedAtResponse(id, createdAt)