package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Mark
import com.mephistophels.freelancing.database.entity.MarkRecipient
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MarkDao: AppRepository<Mark> {

    fun findByRecipientAndCustomerIdAndExecutorIdAndOrderId(recipient: MarkRecipient, customerId: Long, executorId:Long, orderId: Long): Optional<Mark>

    fun findByCustomerId(customerId: Long): Optional<Mark>

    fun findByExecutorId(executorId: Long): Optional<Mark>

    fun findByOrderId(orderId: Long): Optional<Mark>

    @Query("SELECT sum(mark.mark)/count(mark) FROM Mark mark WHERE mark.executor.id = ?1")
    fun getUserMiddleMark(userId: Long): Double
}