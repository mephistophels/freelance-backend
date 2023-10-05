package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.model.response.user.ExecutorToOrderResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class OrderMapper(
    private val userMapper: UserMapper
) {

    fun asEntity(request: OrderRequest): Order {
        return Order(
            title = request.title,
            content = request.content,
            price = request.price,
            status = OrderStatus.CREATED
        )
    }

    fun asNullableResponse(entity: Order?): OrderResponse? {
        return if (entity == null) null else asResponse(entity)
    }

    fun asResponse(entity: Order): OrderResponse {
        return OrderResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            title = entity.title,
            content = entity.content,
            price = entity.price,
            status = entity.status,
            customer = userMapper.asResponse(entity.customer),
            executor = userMapper.asNullableResponse(entity.executor)
        )
    }

    fun asPageResponse(page: Page<Order>): PageResponse<OrderResponse> {
        return PageResponse.build(page, ::asResponse)
    }

    fun asExecutorToOrderResponse(executorId: Long,orderId: Long) = ExecutorToOrderResponse(executorId, orderId)
}