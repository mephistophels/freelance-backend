package com.mephistophels.freelancing.model.response.user

import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import java.time.LocalDate
import java.time.LocalDateTime

open class UserMediumResponse(
    id: Long,
    createdAt: LocalDateTime,
    email: String,
    surname: String,
    name: String,
    patronymic: String? = null,
    birthday: LocalDate? = null,
    val bio: String? = null,
    val mark: UserMarkResponse,
) : UserResponse(id, createdAt, email, surname, name, patronymic, birthday)