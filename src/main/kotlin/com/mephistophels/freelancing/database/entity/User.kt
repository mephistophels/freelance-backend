package com.mephistophels.freelancing.database.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "User")
class User(

): AbstractCreatedAtEntity()