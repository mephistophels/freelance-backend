package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Pageable

interface OrderService {
    fun findEntityById(id: Long): Order
    fun getPage(request: PageRequest): PageResponse<OrderResponse>
    fun getById(id: Long): OrderResponse
}