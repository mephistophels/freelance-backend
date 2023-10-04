package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.user.UserFullResponse
import com.mephistophels.freelancing.model.response.user.UserMediumResponse

interface UserService {
    fun createUser(request: RegistrationRequest): User
    fun existByEmail(email: String): Boolean
    fun findEntityByEmail(email: String): User
    fun findEntityById(id: Long): User
    fun getSelfProfile(userId: Long): UserFullResponse
    fun getUserProfile(userId: Long): UserMediumResponse
}