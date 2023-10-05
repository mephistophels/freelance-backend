package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.OrderExecutionRequestEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderExecutionRequestDao : AppRepository<OrderExecutionRequestEntity>, PagingAndSortingRepository<OrderExecutionRequestEntity, Long> {
    fun findAllByOrderId(orderId: Long, pageable: Pageable): Page<OrderExecutionRequestEntity>
    fun existsByExecutorIdAndOrderId(executorId: Long, orderId: Long): Boolean
}