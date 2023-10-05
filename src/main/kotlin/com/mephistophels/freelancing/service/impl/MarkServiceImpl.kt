package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.database.repository.MarkDao
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.database.repository.UserDao
import com.mephistophels.freelancing.mappers.MarkMapper
import com.mephistophels.freelancing.model.request.MarkRequest
import com.mephistophels.freelancing.model.response.MarkResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import com.mephistophels.freelancing.service.MarkService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class MarkServiceImpl(
    private val mapper: MarkMapper,
    private val dao: MarkDao,
    private val userDao: UserDao,
    private val orderDao: OrderDao,
) : MarkService {

    override fun getUserMark(userId: Long): UserMarkResponse {
        val markAsCustomer = dao.getUserMiddleMarkAsCustomer(userId)
        val markAsExecutor = dao.getUserMiddleMarkAsExecutor(userId)
        return mapper.asUserMarkResponse(markAsCustomer = markAsCustomer, markAsExecutor = markAsExecutor)
    }

    override fun createMark(markRequest: MarkRequest): MarkResponse {
        val customer = userDao.findById(markRequest.customerId)
        val executor = userDao.findById(markRequest.executorId)
        val order = orderDao.findById(markRequest.orderId)
        val mark = dao.save(mapper.asEntity(
            markRequest.mark,
            markRequest.message,
            markRequest.recipient,
            customer,
            executor,
            order,))
        return mapper.asMarkResponse(
            mark.mark,
            mark.message,
            mark.recipient,
            mark.customer.id,
            mark.executor.id,
            mark.order.id,
        )

    }
}