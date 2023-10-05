package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderExecutionRequestEntity
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.model.request.OrderExecutionRequestRequest
import com.mephistophels.freelancing.database.repository.OrderExecutionRequestDao
import com.mephistophels.freelancing.errors.ApiError
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.OrderExecutionRequestMapper
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderExecutionRequestResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderExecutionRequestService
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.getPrincipal
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
@Transactional
class OrderExecutionRequestServiceImpl(
    @Lazy private val userService: UserService,
    @Lazy private val orderService: OrderService,
    private val dao: OrderExecutionRequestDao,
    private val mapper: OrderExecutionRequestMapper
): OrderExecutionRequestService {

    private fun findEntityById(id: Long): OrderExecutionRequestEntity {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun createRequest(orderId: Long, request: OrderExecutionRequestRequest): OrderExecutionRequestResponse {
        if (dao.existsByExecutorIdAndOrderId(getPrincipal(), orderId)) throw ApiError(HttpStatus.BAD_REQUEST, message = "Нельзя подать заявку дважды")
        val entity = mapper.asEntity(request).apply {
            this.order = orderService.findEntityById(orderId)
            if (this.order.customer.id == getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Нельзя подать заявку на своё задание")
            this.executor = userService.findEntityById(getPrincipal())
        }.also { dao.save(it) }
        return mapper.asResponse(entity)
    }

    override fun getRequestsList(orderId: Long, request: PageRequest): PageResponse<OrderExecutionRequestResponse> {
        val order = orderService.findEntityById(orderId)
        if (order.customer.id != getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        val page = dao.findAllByOrderId(orderId, request.pageable)
        return mapper.asPageResponse(page)
    }

    override fun acceptRequest(orderId: Long, requestId: Long): OrderExecutionRequestResponse {
        val order = orderService.findEntityById(orderId)
        if (order.executor != null) throw ApiError(HttpStatus.BAD_REQUEST, message = "Этот заказ уже выполняется")
        if (order.customer.id != getPrincipal()) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        val request = findEntityById(requestId)
        if (request.order.id != orderId) throw ApiError(HttpStatus.BAD_REQUEST, message = "Доступ запрещён")
        order.executor = request.executor
        order.status = OrderStatus.IN_PROGRESS
        return mapper.asResponse(request)
    }
}