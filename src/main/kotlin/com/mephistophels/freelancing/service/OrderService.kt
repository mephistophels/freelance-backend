package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Pageable

interface OrderService {
    fun findEntityById(id: Long): Order
    fun getPage(page: Pageable): PageResponse<OrderResponse>
}