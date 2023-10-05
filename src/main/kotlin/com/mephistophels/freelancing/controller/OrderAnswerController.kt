package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.request.OrderAnswerRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderAnswerResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderAnswerService
import com.mephistophels.freelancing.util.API_VERSION_1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_VERSION_1/order/{orderId}/answers")
class OrderAnswerController(
    private val service: OrderAnswerService
) {

    @GetMapping("/list")
    fun getAnswersList(@PathVariable orderId: Long, request: PageRequest): PageResponse<OrderAnswerResponse> {
        return service.getListAnswers(orderId, request)
    }

    @PostMapping()
    fun createAnswer(@PathVariable orderId: Long, @RequestBody request: OrderAnswerRequest): OrderAnswerResponse {
        return service.createAnswer(orderId, request)
    }

    @PostMapping("/{answerId}/accept")
    fun acceptAnswer(@PathVariable answerId: Long, @PathVariable orderId: Long): OrderAnswerResponse {
        return service.acceptAnswer(answerId)
    }
}