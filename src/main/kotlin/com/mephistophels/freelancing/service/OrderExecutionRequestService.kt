package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.model.request.OrderExecutionRequestRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderExecutionRequestResponse
import com.mephistophels.freelancing.model.response.common.PageResponse

interface OrderExecutionRequestService {
    fun createRequest(orderId: Long, request: OrderExecutionRequestRequest): OrderExecutionRequestResponse
    fun getRequestsList(orderId: Long, request: PageRequest): PageResponse<OrderExecutionRequestResponse>
    fun acceptRequest(orderId: Long, requestId: Long): OrderExecutionRequestResponse
}