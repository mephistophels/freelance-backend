package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.ExecutorToOrderRequest
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.request.StatusUpdateRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.model.response.user.ExecutorToOrderResponse
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.util.API_VERSION_1
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$API_VERSION_1/order")
class OrderController(
    private val service: OrderService
) {
    @PostMapping
    fun createOrder(@Valid @RequestBody request: OrderRequest): OrderResponse {
        return service.createOrder(request)
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): OrderResponse {
        return service.getById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): OrderResponse {
        return service.deleteOrder(id)
    }

    @GetMapping("/list/my/created")
    fun getListMyCreatedOrders(request: PageRequest): PageResponse<OrderResponse> {
        return service.getListMyCreatedOrders(request)
    }

    @GetMapping("/list/my/inprogress")
    fun getListMyDoingOrders(request: PageRequest): PageResponse<OrderResponse> {
        return service.getListMyDoingOrders(request)
    }

    @GetMapping("/list")
    fun getOrderList(request: PageRequest): PageResponse<OrderResponse> {
        return service.getPageCreatedOrders(request)
    }

    @PostMapping("/executor_request")
    fun createExecutorRequest(@RequestBody request: ExecutorToOrderRequest): ExecutorToOrderResponse {
        return service.createExecutorToOrderRequest(request)
    }

    @PutMapping("/executor_request")
    fun addExecutor(@RequestBody request: ExecutorToOrderRequest): ExecutorToOrderResponse {
        return service.createExecutorToOrder(request)
    }

    @GetMapping("/executor_request/{id}")
    fun getExecutorRequests(@PathVariable id: Long) : List<Long>?{
        return service.getExecutorToOrderRequest(id)
    }

    @PutMapping("/status")
    fun updateStatus(@RequestBody request: StatusUpdateRequest): OrderResponse{
        return service.changeStatus(request)
    }
}