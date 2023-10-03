package com.mephistophels.freelancing.database.entity

import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

class Mark(

    @Column(name = "mark", nullable = false)
    var mark:Int,

    @Column(name = "message", nullable = true)
    var message : String,

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customerId")
    var customer: User,

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne()
    @JoinColumn(name = "executorId")
    var executor: User

) : AbstractCreatedAtEntity() {

}