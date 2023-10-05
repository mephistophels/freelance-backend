package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "OrderAnswer")
class OrderAnswerEntity(
    @Column(name = "content", nullable = false, length = 5_000)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderAnswerStatus = OrderAnswerStatus.CHECKING

): AbstractCreatedAtEntity() {
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "orderId")
    lateinit var order: Order
}