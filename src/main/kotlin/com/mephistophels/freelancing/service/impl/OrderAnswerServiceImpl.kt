package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Balance
import com.mephistophels.freelancing.database.entity.OrderAnswerStatus
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.database.repository.OrderAnswerDao
import com.mephistophels.freelancing.errors.ApiError
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.OrderAnswerMapper
import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.request.OrderAnswerRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderAnswerResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.BalanceService
import com.mephistophels.freelancing.service.OrderAnswerService
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.getPrincipal
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Lazy
import org.springframework.data.jpa.repository.Modifying
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
@Transactional
class OrderAnswerServiceImpl(
    @Lazy private val mapper: OrderAnswerMapper,
    @Lazy private val orderService: OrderService,
    @Lazy private val balanceService: BalanceService,
    private val dao: OrderAnswerDao,
    @Lazy private val userService: UserService
): OrderAnswerService {

    private fun findEntityById(id: Long) = dao.findById(id).orElseThrow { ResourceNotFoundException(id) }

    override fun getListAnswers(orderId: Long, request: PageRequest): PageResponse<OrderAnswerResponse> {
        val order = orderService.findEntityById(orderId)
        if (order.customer.id != getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        val entity = dao.findAllByOrderId(orderId, request.pageable)
        return mapper.asListResponse(entity)
    }

    override fun createAnswer(orderId: Long, request: OrderAnswerRequest): OrderAnswerResponse {
        val order = orderService.findEntityById(orderId)
        if (order.customer.id == getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        val entity = mapper.asEntity(request).apply {
            this.order = order
        }.also { dao.save(it) }
        entity.order.status = OrderStatus.DONE
        return mapper.asResponse(entity)
    }

    @Modifying
    override fun acceptAnswer(answerId: Long): OrderAnswerResponse {
        val entity = findEntityById(answerId)
        if (entity.order.customer.id != getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        if (entity.order.status == OrderStatus.ACCEPTED) throw ApiError(HttpStatus.BAD_REQUEST, message = "Ответ уже одобрен")
        entity.status = OrderAnswerStatus.ACCEPTED
        orderService.changeStatus(entity.order.id, OrderStatus.ACCEPTED)
        balanceService.save(Balance(-entity.order.price).apply {
            this.user = entity.order.customer
            this.order = entity.order
        })
        balanceService.save(Balance(entity.order.price).apply {
            this.user = entity.order.executor!!
            this.order = entity.order
        })
        return mapper.asResponse(entity)
    }
}