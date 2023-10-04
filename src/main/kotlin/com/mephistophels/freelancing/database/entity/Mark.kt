package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "MarkTable")
class Mark(

    @Column(name = "mark", nullable = false)
    var mark:Int,

    @Column(name = "message", nullable = true)
    var message : String,

    @Column(name = "recipient", nullable = false)
    var recipient: MarkRecipient,


) : AbstractCreatedAtEntity() {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    lateinit var customer: User

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "executorId")
    lateinit var executor: User

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderId")
    lateinit var order: Order
}
