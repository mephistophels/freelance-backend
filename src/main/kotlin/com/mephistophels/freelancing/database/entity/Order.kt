package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.type.EntityType


@Entity
@Table(name = "OrderTable")
class Order(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "body", nullable = false)
    var body: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus,
): AbstractCreatedAtEntity() {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    lateinit var user: User

    @OneToMany(mappedBy = "order")
    var message: Set<Message> = HashSet<Message>()

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "executorId")
    lateinit var executor: User

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "executorId")],
        name = "ExecutorRequestToOrderTable",
    )
    var executors: MutableList<User> = mutableListOf()

}
