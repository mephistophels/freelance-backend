package com.mephistophels.freelancing.model.request

class OrderRequest(
    val title: String,
    val content: String,
    val price: Int = 0
)