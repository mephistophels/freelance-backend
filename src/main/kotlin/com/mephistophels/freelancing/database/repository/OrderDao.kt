package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface OrderDao : AppRepository<Order> {
    fun findPage(pageable: Pageable): Page<Order>
}