package com.mephistophels.freelancing.database.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "UserTable")
class User(
    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "surname", nullable = false)
    var surname: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "patronymic", nullable = true)
    var patronymic: String? = null,

    @Column(name = "birthday", nullable = true)
    var birthday: LocalDate? = null,
) : AbstractCreatedAtEntity() {

    @Column(name = "hash", nullable = false)
    var hash: String? = null

    @OneToMany(mappedBy = "user")
    var history: Set<Balance> = HashSet<Balance>()

    @OneToMany(mappedBy = "customer")
    var customerMark: MutableSet<Mark> = HashSet<Mark>()

    @OneToMany(mappedBy = "executor")
    var order: MutableSet<Order> = HashSet<Order>()

    @OneToMany(mappedBy = "executor")
    var executorMark: Set<Mark> = HashSet<Mark>()

    @OneToMany(mappedBy = "sender")
    var sendedMessage: Set<Message> = HashSet<Message>()

    @OneToMany(mappedBy = "recipient")
    var recievedMessage: Set<Message> = HashSet<Message>()
}