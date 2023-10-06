package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.repository.MarkDao
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.mappers.MarkMapper
import com.mephistophels.freelancing.model.request.MarkRequest
import com.mephistophels.freelancing.model.response.MarkResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import com.mephistophels.freelancing.service.MarkService
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.service.UserService
import org.springframework.stereotype.Service

@Service
class MarkServiceImpl(
    private val mapper: MarkMapper,
    private val dao: MarkDao,
    private val userService: UserService,
    private val orderService: OrderService,
) : MarkService {

    override fun getUserMark(userId: Long): UserMarkResponse {
        val markAsCustomer = dao.getUserMiddleMarkAsCustomer(userId)
        val markAsExecutor = dao.getUserMiddleMarkAsExecutor(userId)
        return mapper.asUserMarkResponse(markAsCustomer = markAsCustomer, markAsExecutor = markAsExecutor)
    }

    override fun createMark(markRequest: MarkRequest): MarkResponse {
        val customer = userService.findEntityById(markRequest.customerId)
        val executor = userService.findEntityById(markRequest.executorId)
        val order = orderService.findEntityById(markRequest.orderId)
        val mark = mapper.asEntity(
            markRequest.mark,
            markRequest.message,
            markRequest.recipient,
            customer,
            executor,
            order
        ).also { dao.save(it) }

        return mapper.asMarkResponse(
            mark.mark,
            mark.message,
            mark.recipient,
            mark.order.customer.id,
            mark.order.executor!!.id,
            mark.order.id,
        )
    }
}