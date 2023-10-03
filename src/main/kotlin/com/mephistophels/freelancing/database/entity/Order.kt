package com.mephistophels.freelancing.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "OrderTable")
class Order(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "body", nullable = false)
    var body: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Column(name = "status", nullable = false)
    var status: String,
): AbstractCreatedAtEntity()
