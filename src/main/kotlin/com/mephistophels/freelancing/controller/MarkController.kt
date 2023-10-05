package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.request.MarkRequest
import com.mephistophels.freelancing.model.response.MarkResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse
import com.mephistophels.freelancing.service.MarkService
import com.mephistophels.freelancing.util.API_VERSION_1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_VERSION_1/mark")
class MarkController(
    private val service:MarkService
) {

    @GetMapping("/{id}")
    fun getMark(@PathVariable id: Long): UserMarkResponse {
        return service.getUserMark(id)
    }

    @PostMapping
    fun createMark(@RequestBody markRequest: MarkRequest): MarkResponse{
        return service.createMark(markRequest)
    }
}