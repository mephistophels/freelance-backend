package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "MessageTable")
class Message(
    @Column(name = "message", nullable = true)
    var message : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "recipient", nullable = false)
    var recipient: MarkRecipient
) : AbstractCreatedAtEntity(){
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderId")
    lateinit var order: Order
}