package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.model.request.BalanceOperationRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.BalanceOperationResponse
import com.mephistophels.freelancing.model.response.UserBalanceResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.BalanceService
import com.mephistophels.freelancing.util.API_VERSION_1
import com.mephistophels.freelancing.util.getPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$API_VERSION_1/balance")
class BalanceController(
    private val service: BalanceService,
) {
    @GetMapping("/list")
    fun balanceOperationList(request: PageRequest): PageResponse<BalanceOperationResponse> {
        return service.getList(request)
    }

    @PostMapping("/replenish")
    fun replenishBalance(@RequestBody request: BalanceOperationRequest): BalanceOperationResponse {
        return service.replenishBalance(request, getPrincipal())
    }

     @PostMapping("/withdraw")
    fun withdrawFromBalance(@RequestBody request: BalanceOperationRequest): BalanceOperationResponse {
        return service.withdrawFromBalance(request, getPrincipal())
    }

    @GetMapping("/info")
    fun getBalanceInfo(): UserBalanceResponse {
        return service.getBalanceAmount(getPrincipal())
    }

}