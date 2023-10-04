package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Order
import com.mephistophels.freelancing.database.entity.OrderStatus
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderDao : AppRepository<Order>, PagingAndSortingRepository<Order, Long> {
    fun findAllByCustomerId(customerId: Long): Optional<List<Order>>

//    fun updateStatusById(id: Long)

//    fun updateExecutorIdById(executorId:Long, id:Long)
}