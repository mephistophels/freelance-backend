package com.mephistophels.freelancing.model.request

import jakarta.validation.constraints.Size

class OrderExecutionRequestRequest(
    @field:Size(min = 1, max = 5_000)
    val content: String
)