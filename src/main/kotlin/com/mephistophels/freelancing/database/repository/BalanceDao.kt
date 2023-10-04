package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Balance
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BalanceDao : AppRepository<Balance>, PagingAndSortingRepository<Balance, Long> {
    @Query("SELECT sum(b.price) FROM Balance b WHERE b.user.id = ?1")
    fun getUserBalance(userId: Long): Long

}