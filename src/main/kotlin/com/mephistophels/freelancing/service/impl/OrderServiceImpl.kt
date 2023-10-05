package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.database.repository.OrderDao
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.OrderMapper
import com.mephistophels.freelancing.model.request.ExecutorToOrderRequest
import com.mephistophels.freelancing.model.request.OrderRequest
import com.mephistophels.freelancing.model.request.PageRequest
import com.mephistophels.freelancing.model.request.StatusUpdateRequest
import com.mephistophels.freelancing.model.response.OrderResponse
import com.mephistophels.freelancing.model.response.common.PageResponse
import com.mephistophels.freelancing.model.response.user.ExecutorToOrderResponse
import com.mephistophels.freelancing.service.OrderService
import com.mephistophels.freelancing.service.UserService
import com.mephistophels.freelancing.util.getPrincipal
import jakarta.transaction.Transactional
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
    override fun changeStatus(request: StatusUpdateRequest): OrderResponse {
        val entity = findEntityById(request.orderId)
        entity.status = request.status
        return mapper.asResponse(entity)
    }

    // 2) мои задания (создал)
    override fun getListMyCreatedOrders(request: PageRequest): PageResponse<OrderResponse> {
        val page = dao.findAllByCustomerId(getPrincipal(), request.pageable)
        return mapper.asPageResponse(page)
    }

    // 1) мои задания (делаю)
    override fun getListMyDoingOrders(request: PageRequest): PageResponse<OrderResponse> {
        val page = dao.findAllByExecutorId(getPrincipal(), request.pageable)
        return mapper.asPageResponse(page)
    }

    @Modifying
    override fun createExecutorToOrderRequest(request: ExecutorToOrderRequest): ExecutorToOrderResponse {
        val order = findEntityById(request.orderId)
        val executor = userService.findEntityById(request.executorId)
        order.executors.add(executor)
        return mapper.asExecutorToOrderResponse(request.orderId, request.executorId)
    }

    @Modifying
    override fun createExecutorToOrder(request: ExecutorToOrderRequest): ExecutorToOrderResponse {
        val order = findEntityById(request.orderId)
        val executor = userService.findEntityById(request.executorId)
        order.executor = executor
        order.status = OrderStatus.IN_PROGRESS
        return mapper.asExecutorToOrderResponse(executor.id, order.id)
    }

    override fun getExecutorToOrderRequest(orderId: Long): List<Long>? {
        val order= findEntityById(orderId)
        var res = mutableListOf<Long>()
        for (executor in order.executors){
            res.add(executor.id)
        }
        return res
    }

    override fun findEntityById(id: Long): Order {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun getById(id: Long): OrderResponse {
        return mapper.asResponse(findEntityById(id))
    }

    // 3) те, у которых нет исполнителя
    override fun getPageCreatedOrders(request: PageRequest): PageResponse<OrderResponse> {
        val page = dao.findAllByStatusIs(OrderStatus.CREATED, request.pageable)
        return mapper.asPageResponse(page)
    }

}