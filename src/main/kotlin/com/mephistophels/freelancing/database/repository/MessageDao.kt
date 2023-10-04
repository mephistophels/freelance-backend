package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Message
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageDao : AppRepository<Message>{

    fun findByRecipientIdAndSenderIdAndOrderId(recipientId: Long, senderId: Long, orderId: Long): Optional<List<Message>>

    fun findByOrderId(orderId: Long): Optional<List<Message>>
}