package com.mephistophels.freelancing.service.impl.auth

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.errors.ApiError
import com.mephistophels.freelancing.mappers.UserMapper
import com.mephistophels.freelancing.model.request.LoginRequest
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.LoginResponse
import com.mephistophels.freelancing.model.response.user.UserResponse
import com.mephistophels.freelancing.service.AuthService
import com.mephistophels.freelancing.service.UserService
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val jwtHelper: JwtHelper,
    private val userService: UserService,
    private val mapper: UserMapper,
    private val encoder: PasswordEncoder
) : AuthService {

    @Transactional
    override fun login(request: LoginRequest): LoginResponse {
        val user = loginUser(request)
        val jwt = jwtHelper.generateAccessToken(user)
        return LoginResponse(jwt)
    }

    @Modifying
    @Transactional
    override fun register(request: RegistrationRequest): UserResponse {
        val user = userService.createUser(request)
        return mapper.asResponse(user)
    }

    private fun loginUser(request: LoginRequest): User {
        val user = userService.findEntityByEmail(request.email)
        if (!encoder.matches(request.password, user.hash)) {
            throw ApiError(HttpStatus.UNAUTHORIZED, "Неправильный пароль")
        }
        return user
    }



}