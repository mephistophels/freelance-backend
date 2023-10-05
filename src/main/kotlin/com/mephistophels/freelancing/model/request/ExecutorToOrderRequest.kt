package com.mephistophels.freelancing.model.request

data class ExecutorToOrderRequest(
    val executorId: Long,
    val orderId: Long,
)