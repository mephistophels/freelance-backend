package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Mark
import com.mephistophels.freelancing.database.entity.MarkRecipient
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MarkDao: AppRepository<Mark> {

    fun findByRecipientAndOrderCustomerIdAndOrderExecutorIdAndOrderId(recipient: MarkRecipient, customerId: Long, executorId:Long, orderId: Long): Optional<Mark>

    fun findByOrderCustomerId(customerId: Long): Optional<Mark>

    fun findByOrderExecutorId(executorId: Long): Optional<Mark>

    fun findByOrderId(orderId: Long): Optional<Mark>

    @Query("SELECT sum(mark.mark)/count(mark) FROM Mark mark WHERE mark.order.executor.id = ?1 AND mark.recipient = ?2")
    fun getUserMiddleMarkAsExecutor(userId: Long, markRecipient: MarkRecipient = MarkRecipient.EXECUTOR): Double?

    @Query("SELECT sum(mark.mark)/count(mark) FROM Mark mark WHERE mark.order.customer.id = ?1 AND mark.recipient = ?2")
    fun getUserMiddleMarkAsCustomer(userId: Long, markRecipient: MarkRecipient = MarkRecipient.CUSTOMER): Double?

}