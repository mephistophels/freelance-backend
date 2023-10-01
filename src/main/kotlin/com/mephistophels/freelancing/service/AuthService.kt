package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.model.request.LoginRequest
import com.mephistophels.freelancing.model.response.LoginResponse

interface AuthService {
    fun login(request: LoginRequest): LoginResponse
}