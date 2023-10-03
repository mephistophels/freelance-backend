package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.repository.BalanceDao
import com.mephistophels.freelancing.errors.ApiError
import com.mephistophels.freelancing.errors.TooLittleBalanceException
import com.mephistophels.freelancing.mappers.BalanceMapper
import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.BalanceOperationResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.BalanceService
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.getPrincipal
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
@Transactional
class BalanceServiceImpl(
    private val dao: BalanceDao,
    private val userService: UserService,
    private val mapper: BalanceMapper,
) : BalanceService {

    override fun getList(request: PageRequest): PageResponse<BalanceOperationResponse> {
        val page = dao.findAll(request.pageable)
        return mapper.asPageResponse(page)
    }

    override fun replenishBalance(request: BalanceOperationRequest): BalanceOperationResponse {
        if (request.price <= 0) throw ApiError(status = HttpStatus.BAD_REQUEST, "Нельзя пополнить на отрицательную сумму")
        val user = userService.findEntityById(getPrincipal())
        val entity = dao.save(mapper.asEntity(request).apply {
            this.user = user
        })
        return mapper.asResponse(entity)
    }

    override fun withdrawFromBalance(request: BalanceOperationRequest): BalanceOperationResponse {
        if (request.price <= 0) throw ApiError(status = HttpStatus.BAD_REQUEST, "Нельзя вывести отрицательную сумму")
        val user = userService.findEntityById(getPrincipal())
        val balance = dao.getUserBalance(user.id)
        if (request.price > balance) throw TooLittleBalanceException()
        val entity = dao.save(mapper.asEntity(request).apply {
            this.price = -price
            this.user = user
        })
        return mapper.asResponse(entity)
    }
}