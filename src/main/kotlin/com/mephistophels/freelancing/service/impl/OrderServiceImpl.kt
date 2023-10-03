package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.OrderMapper
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val dao: OrderDao,
    private val mapper: OrderMapper
) : OrderService {
    override fun findEntityById(id: Long): Order {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun getById(id: Long): OrderResponse {
        return mapper.asResponse(findEntityById(id))
    }

    override fun getPage(request: PageRequest): PageResponse<OrderResponse> {
        val page = dao.findPage(request.pageable)
        return mapper.asPageResponse(page)
    }

}