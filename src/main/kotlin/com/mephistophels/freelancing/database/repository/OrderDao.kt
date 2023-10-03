package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderDao : AppRepository<Order>, PagingAndSortingRepository<Order, Long> {

}