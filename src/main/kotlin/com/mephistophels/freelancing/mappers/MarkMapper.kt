package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.Mark
import com.mephistophels.freelancing.database.entity.MarkRecipient
import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.response.MarkResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@Component
class MarkMapper {

    fun asUserMarkResponse(markAsCustomer: Double?, markAsExecutor: Double?) = UserMarkResponse(markAsCustomer = markAsCustomer, markAsExecutor = markAsExecutor)

    fun asMarkResponse(
        mark: Int,
        message: String,
        recipient: MarkRecipient,
        customerId: Long,
        executorId: Long,
        orderId: Long,
    ) : MarkResponse{
        return MarkResponse(
            mark = mark,
            message = message,
            recipient = recipient,
            customerId = customerId,
            executorId = executorId,
            orderId = orderId
        )
    }

    fun asEntity(
        mark: Int,
        message: String,
        recipient: MarkRecipient,
        customer: Optional<User>,
        executor: Optional<User>,
        order: Optional<Order>,
    ): Mark{
        return Mark(
            mark = mark,
            message = message,
            recipient = recipient,
        ).apply {
            this.customer = customer.get()
            this.executor = executor.get()
            this.order = order.get()
        }
    }
}