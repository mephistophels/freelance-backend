package com.mephistophels.freelancing.model.response.user

import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import java.time.LocalDate
import java.time.LocalDateTime

class UserFullResponse(
    id: Long,
    createdAt: LocalDateTime,
    email: String,
    surname: String,
    name: String,
    patronymic: String? = null,
    birthday: LocalDate? = null,
    bio: String? = null,
    mark: UserMarkResponse,
    val balance: UserBalanceResponse,
) : UserMediumResponse(id, createdAt, email, surname, name, patronymic, birthday, mark, bio)