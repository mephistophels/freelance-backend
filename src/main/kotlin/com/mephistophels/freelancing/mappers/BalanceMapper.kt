package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Balance
import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.response.BalanceOperationResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BalanceMapper {
    fun asEntity(request: BalanceOperationRequest): Balance {
        return Balance(request.price)
    }

    fun asResponse(entity: Balance): BalanceOperationResponse {
        return BalanceOperationResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            price = entity.price
        )
    }

    fun asPageResponse(page: Page<Balance>): PageResponse<BalanceOperationResponse> {
        return PageResponse.build(page, ::asResponse)
    }
}