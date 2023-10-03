package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.OrderMapper
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.getPrincipal
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Lazy
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service

@Service
@Transactional
class OrderServiceImpl(
    private val dao: OrderDao,
    private val mapper: OrderMapper,
    private val userService: UserService
) : OrderService {

    override fun createOrder(request: OrderRequest): OrderResponse {
        val user = userService.findEntityById(getPrincipal())
        val entity = mapper.asEntity(request).apply {
            this.customer = user
        }
        return mapper.asResponse(dao.save(entity))
    }

    override fun deleteOrder(id: Long): OrderResponse {
        val entity = findEntityById(id)
        dao.delete(entity)
        return mapper.asResponse(entity)
    }

    @Modifying
    override fun changeStatus(id: Long, status: OrderStatus): Order {
        val entity = findEntityById(id)
        entity.status = status
        return entity
    }

    override fun findEntityById(id: Long): Order {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun getById(id: Long): OrderResponse {
        return mapper.asResponse(findEntityById(id))
    }

    override fun getPage(request: PageRequest): PageResponse<OrderResponse> {
        val page = dao.findAll(request.pageable)
        return mapper.asPageResponse(page)
    }

}