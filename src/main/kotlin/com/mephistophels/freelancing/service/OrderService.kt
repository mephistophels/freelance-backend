package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Pageable

interface OrderService {
    fun findEntityById(id: Long): Order
    fun getPageCreatedOrders(request: PageRequest): PageResponse<OrderResponse>
    fun getById(id: Long): OrderResponse
    fun createOrder(request: OrderRequest): OrderResponse
    fun deleteOrder(id: Long): OrderResponse
    fun changeStatus(id: Long, status: OrderStatus): Order
    fun getListMyCreatedOrders(request: PageRequest): PageResponse<OrderResponse>
    fun getListMyDoingOrders(request: PageRequest): PageResponse<OrderResponse>
}