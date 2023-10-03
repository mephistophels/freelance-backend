package com.mephistophels.freelancing.model.request

import com.mephistophels.freelancing.database.entity.OrderStatus

class OrderRequest(
    val title: String,
    val body: String,
    val price: Int,
)