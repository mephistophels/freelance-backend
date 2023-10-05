package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.OrderExecutionRequestEntity
import com.mephistophels.freelancing.model.request.OrderExecutionRequestRequest
import com.mephistophels.freelancing.model.response.OrderExecutionRequestResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class OrderExecutionRequestMapper(
    @Lazy private val userMapper: UserMapper
) {
    fun asEntity(request: OrderExecutionRequestRequest): OrderExecutionRequestEntity {
        return OrderExecutionRequestEntity(
            content = request.content
        )
    }

    fun asResponse(request: OrderExecutionRequestEntity): OrderExecutionRequestResponse {
        return OrderExecutionRequestResponse(
            id = request.id,
            createdAt = request.createdAt,
            content = request.content,
            executor = userMapper.asResponse(request.executor)
        )
    }

    fun asPageResponse(page: Page<OrderExecutionRequestEntity>): PageResponse<OrderExecutionRequestResponse> {
        return PageResponse.build(page, ::asResponse)
    }
}