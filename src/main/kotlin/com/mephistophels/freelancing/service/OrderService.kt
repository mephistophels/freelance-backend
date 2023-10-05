package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.ExecutorToOrderRequest
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.request.StatusUpdateRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.model.response.user.ExecutorToOrderResponse
import org.springframework.data.domain.Pageable

interface OrderService {
    fun findEntityById(id: Long): Order
    fun getPageCreatedOrders(request: PageRequest): PageResponse<OrderResponse>
    fun getById(id: Long): OrderResponse
    fun createOrder(request: OrderRequest): OrderResponse
    fun deleteOrder(id: Long): OrderResponse
    fun changeStatus(request: StatusUpdateRequest): OrderResponse
    fun getListMyCreatedOrders(request: PageRequest): PageResponse<OrderResponse>
    fun getListMyDoingOrders(request: PageRequest): PageResponse<OrderResponse>

    fun createExecutorToOrderRequest(request: ExecutorToOrderRequest): ExecutorToOrderResponse

    fun getExecutorToOrderRequest(orderId: Long): List<Long>?

    fun createExecutorToOrder(request: ExecutorToOrderRequest): ExecutorToOrderResponse
}