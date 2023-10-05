package com.mephistophels.freelancing.database.entity

import com.mephistophels.freelancing.model.response.common.AbstractCreatedAtResponse
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "OrderExecutionRequest")
class OrderExecutionRequestEntity(
    @Column(name = "content", nullable = false)
    val content: String
) : AbstractCreatedAtEntity() {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "orderId")
    lateinit var order: Order

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "executorId")
    lateinit var executor: User

}