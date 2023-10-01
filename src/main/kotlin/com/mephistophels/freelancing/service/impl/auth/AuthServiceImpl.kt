package com.mephistophels.freelancing.service.impl.auth

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.LoginRequest
import com.mephistophels.freelancing.model.response.LoginResponse
import com.mephistophels.freelancing.service.AuthService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val jwtHelper: JwtHelper
) : AuthService {
    override fun login(request: LoginRequest): LoginResponse {
        val user = loginUser(request)
        val jwt = jwtHelper.generateAccessToken(user)
        return LoginResponse(jwt)
    }

    // TODO (Viktor Kokorev): get user by login. Compare password-hash with hash in db, return user entity if all is ok
    private fun loginUser(request: LoginRequest): User {
        val user = User()
        user.id = 1 // TODO: only for testing!
        return user
    }

}