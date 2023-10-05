package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.model.request.OrderAnswerRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderAnswerResponse
import com.mephistophels.freelancing.model.response.common.PageResponse

interface OrderAnswerService {
    fun getListAnswers(orderId: Long, request: PageRequest): PageResponse<OrderAnswerResponse>
    fun createAnswer(orderId: Long, request: OrderAnswerRequest): OrderAnswerResponse
    fun acceptAnswer(answerId: Long): OrderAnswerResponse
}