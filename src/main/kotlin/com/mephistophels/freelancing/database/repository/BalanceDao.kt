package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Balance
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BalanceDao : AppRepository<Balance>, PagingAndSortingRepository<Balance, Long>, CrudRepository<Balance, Long> {
    @Query(value = "SELECT sum(b.price) FROM Balance b WHERE b.user.id = ?1")
    fun getUserBalance(userId: Long): Long

    @Query(value ="select b.price from Balance b where b.user.id = ?1")
    fun getUserBalanceHistory(userId: Long) : List<Int>

    @Modifying
    @Query(value = "insert into BalanceTable (userId, price) values (:userId, :price)", nativeQuery = true)
    @Transactional
    fun changeBalance(@Param("userId") userId: Long, @Param("price") price: Int)
}