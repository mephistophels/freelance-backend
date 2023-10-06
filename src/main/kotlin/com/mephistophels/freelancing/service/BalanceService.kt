package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.Balance
import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.BalanceOperationResponse
import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.common.PageResponse

interface BalanceService {
    fun getList(request: PageRequest): PageResponse<BalanceOperationResponse>
    fun replenishBalance(request: BalanceOperationRequest, userId: Long, order: Order? = null): BalanceOperationResponse
    fun withdrawFromBalance(request: BalanceOperationRequest, userId: Long, order: Order? = null): BalanceOperationResponse
    fun getBalanceAmount(user: User): UserBalanceResponse
    fun getBalanceAmount(userId: Long): UserBalanceResponse
    fun save(entity: Balance): Balance
}