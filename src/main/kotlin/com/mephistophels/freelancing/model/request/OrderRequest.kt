package com.mephistophels.freelancing.model.request

import jakarta.validation.constraints.Size

class OrderRequest(
    @field:Size(min = 1, max = 255)
    val title: String,
    @field:Size(min = 1, max = 5_000)
    val content: String,
    @field:Size(min = 1)
    val price: Int = 0
)