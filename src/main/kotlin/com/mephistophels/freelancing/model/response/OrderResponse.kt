package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import com.mephistophels.freelancing.model.response.common.AbstractResponse
import java.time.LocalDateTime

class OrderResponse(
    id: Long,
    createdAt: LocalDateTime


): AbstractCreatedAtResponse(id, createdAt)