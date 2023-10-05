package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.OrderAnswerEntity
import com.mephistophels.freelancing.model.request.OrderAnswerRequest
import com.mephistophels.freelancing.model.response.OrderAnswerResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class OrderAnswerMapper(
    @Lazy private val userMapper: UserMapper
) {
    fun asEntity(request: OrderAnswerRequest): OrderAnswerEntity {
        return OrderAnswerEntity(
            content = request.content
        )
    }

    fun asResponse(entity: OrderAnswerEntity): OrderAnswerResponse {
        return OrderAnswerResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            content = entity.content,
            status = entity.status,
            executor = userMapper.asNullableResponse(entity.order.executor)
        )
    }

    fun asListResponse(page: Page<OrderAnswerEntity>): PageResponse<OrderAnswerResponse> {
        return PageResponse.build(page, ::asResponse)
    }
}