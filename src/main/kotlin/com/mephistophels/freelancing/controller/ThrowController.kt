package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.errors.ApiError
import com.mephistophels.freelancing.util.API_VERSION_1
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_VERSION_1)
class ThrowController {
    @GetMapping("/throw")
    fun throwError() {
        throw ApiError(status = HttpStatus.INTERNAL_SERVER_ERROR, message = "Любимая пятисотая")
    }
}