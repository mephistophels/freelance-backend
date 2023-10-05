package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.OrderAnswerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderAnswerDao : AppRepository<OrderAnswerEntity>, PagingAndSortingRepository<OrderAnswerEntity, Long> {
    fun findAllByOrderId(orderId: Long, pageable: PageRequest): Page<OrderAnswerEntity>
}