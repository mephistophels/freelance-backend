package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import com.mephistophels.freelancing.model.response.user.UserFullResponse
import com.mephistophels.freelancing.model.response.user.UserMediumResponse
import com.mephistophels.freelancing.model.response.user.UserResponse
import com.mephistophels.freelancing.service.BalanceService
import com.mephistophels.freelancing.service.MarkService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component


@Component
class UserMapper(
    @Lazy private val markService: MarkService
) {
    fun asEntity(request: RegistrationRequest): User {
        return User(
            email = request.email,
            name = request.name,
            patronymic = request.patronymic,
            surname = request.surname,
            birthday = request.birthday,
            bio = request.bio
        )
    }
    fun asNullableResponse(entity: User?): UserResponse? {
        return if (entity == null) null else asResponse(entity)
    }

    fun asResponse(entity: User): UserResponse {
        return UserResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            email = entity.email,
            name = entity.name,
            patronymic = entity.patronymic,
            surname = entity.surname,
            birthday = entity.birthday,
            mark = markService.getUserMark(entity.id)
        )
    }

    fun asUserFullResponse(entity: User, balance: UserBalanceResponse, mark: UserMarkResponse) : UserFullResponse {
        return UserFullResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            email = entity.email,
            name = entity.name,
            patronymic = entity.patronymic,
            surname = entity.surname,
            birthday = entity.birthday,
            bio = entity.bio,
            balance = balance,
            mark = mark
        )
    }

    fun asUserMediumResponse(entity: User, mark: UserMarkResponse) : UserMediumResponse {
        return UserMediumResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            email = entity.email,
            name = entity.name,
            patronymic = entity.patronymic,
            surname = entity.surname,
            birthday = entity.birthday,
            bio = entity.bio,
            mark = mark
        )
    }
}