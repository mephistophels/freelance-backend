package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Balance
import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderDao : AppRepository<Order>, PagingAndSortingRepository<Order, Long>, CrudRepository<Order, Long> {

    fun findAllByCustomerId(customerId: Long, pageable: Pageable): Page<Order>

    fun findAllByExecutorId(executorId: Long, pageable: Pageable): Page<Order>

    fun findAllByStatusIs(status: OrderStatus, pageable: Pageable): Page<Order>

    @Modifying
    @Query(value = "update Order o set o.status = ?1 where o.id = ?2")
    @Transactional
    fun updateStatusById(
        status: OrderStatus,
        id: Long,
    )

    @Modifying
    @Query(value = "update Order o set o.executor.id  = ?1 where o.id = ?2")
    @Transactional
    fun updateExecutorIdById(
        executorId: Long,
        id: Long,
    )

    @Modifying
    @Query(
        value = "insert into ExecutorRequestToOrderTable (orderId, executorId) values (:orderId, :executorId)",
        nativeQuery = true
    )
    @Transactional
    fun addExecutorRequest(
        @Param("orderId") orderId: Long,
        @Param("executorId") executorId: Long,
    )
}