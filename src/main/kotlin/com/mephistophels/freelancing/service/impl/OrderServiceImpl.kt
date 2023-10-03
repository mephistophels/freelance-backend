package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val dao: OrderDao
) : OrderService {
    override fun findEntityById(id: Long): Order {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun getPage(pageable: Pageable): PageResponse<OrderResponse> {
        val page = dao.findPage(pageable)
    }

}