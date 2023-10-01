package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.model.request.LoginRequest
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.LoginResponse
import com.mephistophels.freelancing.model.response.UserResponse

interface AuthService {
    fun login(request: LoginRequest): LoginResponse
    fun register(request: RegistrationRequest): UserResponse
}