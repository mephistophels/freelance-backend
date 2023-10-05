package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Balance
import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.response.BalanceOperationResponse
import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BalanceMapper(
    @Lazy private val orderMapper: OrderMapper
) {
    fun asEntity(request: BalanceOperationRequest): Balance {
        return Balance(request.price)
    }

    fun asOperationResponse(entity: Balance): BalanceOperationResponse {
        return BalanceOperationResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            price = entity.price,
            order = orderMapper.asNullableResponse(entity.order)
        )
    }

    fun asBalanceResponse(amount: Int) = UserBalanceResponse(amount = amount)

    fun asPageResponse(page: Page<Balance>): PageResponse<BalanceOperationResponse> {
        return PageResponse.build(page, ::asOperationResponse)
    }
}