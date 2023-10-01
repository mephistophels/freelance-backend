package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.UserResponse
import org.springframework.stereotype.Component


@Component
class UserMapper {
    fun asEntity(request: RegistrationRequest): User {
        return User(
            email = request.email,
            name = request.name,
            patronymic = request.patronymic,
            surname = request.surname,
            birthday = request.birthday
        )
    }
    fun asResponse(entity: User): UserResponse {
        return UserResponse(
            id = entity.id,
            createdAt = entity.createdAt,
            email = entity.email,
            name = entity.name,
            patronymic = entity.patronymic,
            surname = entity.surname,
            birthday = entity.birthday
        )
    }
}