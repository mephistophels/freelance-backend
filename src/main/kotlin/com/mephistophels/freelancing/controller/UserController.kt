package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.response.user.UserFullResponse
import com.mephistophels.freelancing.model.response.user.UserMediumResponse
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.API_VERSION_1
import com.mephistophels.freelancing.util.getPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_VERSION_1/user")
class UserController(
    private val service: UserService
) {

    @GetMapping("/me")
    fun getSelfProfile(): UserFullResponse {
        return service.getSelfProfile(getPrincipal())
    }

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: Long): UserMediumResponse {
        return service.getUserProfile(userId)
    }
}