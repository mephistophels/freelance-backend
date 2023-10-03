package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class OrderMapper {

    fun asResponse(entity: Order): OrderResponse {
        return OrderResponse(
            id = entity.id,
            createdAt = entity.createdAt,
        )
    }

    fun asPageResponse(page: Page<Order>): PageResponse<OrderResponse> {
        return PageResponse.build(page, ::asResponse)
    }
}