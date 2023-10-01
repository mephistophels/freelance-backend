package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.request.LoginRequest
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.LoginResponse
import com.mephistophels.freelancing.model.response.UserResponse
import com.mephistophels.freelancing.service.AuthService
import com.mephistophels.freelancing.util.API_PUBLIC
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("$API_PUBLIC/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        return authService.login(request)
    }

    @PostMapping("/registration")
    fun register(@Valid @RequestBody request: RegistrationRequest): UserResponse {
        return authService.register(request)
    }


}