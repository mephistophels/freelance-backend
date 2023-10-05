package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.request.OrderExecutionRequestRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderExecutionRequestResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderExecutionRequestService
import com.mephistophels.freelancing.util.API_VERSION_1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_VERSION_1/order/{orderId}/request")
class OrderExecutionRequestController(
    private val service: OrderExecutionRequestService
) {

    @PostMapping
    fun sendRequest(@PathVariable orderId: Long, @RequestBody request: OrderExecutionRequestRequest): OrderExecutionRequestResponse {
        return service.createRequest(orderId, request)
    }

    @GetMapping("/list")
    fun getListRequests(@PathVariable orderId: Long, request: PageRequest): PageResponse<OrderExecutionRequestResponse> {
        return service.getRequestsList(orderId, request)
    }

    @PostMapping("/{requestId}/accept")
    fun acceptRequest(@PathVariable orderId: Long, @PathVariable requestId: Long): OrderExecutionRequestResponse {
        return service.acceptRequest(orderId, requestId)
    }
}