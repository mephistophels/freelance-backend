package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class OrderMapper(
    private val userMapper: UserMapper
) {

    fun asEntity(request: OrderRequest): Order {
        return Order(
            title = request.title,
            body = request.body,
            price = request.price,
            status = OrderStatus.CREATED
        )
    }

    fun asResponse(entity: Order): OrderResponse {
        return OrderResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            title = entity.title,
            body = entity.body,
            price = entity.price,
            status = entity.status,
            user = userMapper.asResponse(entity.user)
        )
    }

    fun asPageResponse(page: Page<Order>): PageResponse<OrderResponse> {
        return PageResponse.build(page, ::asResponse)
    }
}