package com.mephistophels.freelancing.database.entity

import jakarta.persistence.*
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
): AbstractCreatedAtEntity()