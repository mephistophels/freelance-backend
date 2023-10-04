package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "OrderTable")
class Order(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus,
): AbstractCreatedAtEntity() {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    lateinit var customer: User

    @OneToMany(mappedBy = "order")
    var message: Set<Message> = HashSet<Message>()

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "executorId")
    var executor: User? = null

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "executorId")],
        name = "ExecutorRequestToOrderTable",
    )
    var executors: MutableList<User> = mutableListOf()

    @OneToMany(mappedBy = "order")
    var mark: Set<Mark> = HashSet<Mark>()
}
