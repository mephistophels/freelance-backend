package com.mephistophels.freelancing.model.request

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.RequestParam

class PageRequest(
    @RequestParam val page: Int,
    @RequestParam val size: Int,
    @RequestParam val order: Sort.Direction,
    @RequestParam var field: String,
) {
    var pageable = PageRequest.of(page - 1, size, order, field)

    fun updateRequest(orders: List<Sort.Order>) {
        val sort = Sort.by(orders)
        pageable = PageRequest.of(page - 1, size, sort)
    }
}