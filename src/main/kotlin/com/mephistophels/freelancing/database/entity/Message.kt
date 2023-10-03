package com.mephistophels.freelancing.database.entity

import jakarta.persistence.Column

class Message(

    @Column(name = "message", nullable = true)
    var message : String,


    ) : AbstractCreatedAtEntity(){
}