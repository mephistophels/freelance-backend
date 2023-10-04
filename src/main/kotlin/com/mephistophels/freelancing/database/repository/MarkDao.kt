package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Mark
import com.mephistophels.freelancing.database.entity.MarkRecipient
import java.util.*

interface MarkDao: AppRepository<Mark> {

    fun findByRecipientAndCustomerIdAndExecutorIdAndOrderId(recipient: MarkRecipient, customerId: Long, executorId:Long, orderId: Long): Optional<Mark>

    fun findByCustomerId(customerId: Long): Optional<Mark>

    fun findByExecutorId(executorId: Long): Optional<Mark>

    fun findByOrderId(orderId: Long): Optional<Mark>
}