package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.User
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserDao : AppRepository<User> {
    fun findByEmail(email: String): Optional<User>
    fun existsByEmail(email: String): Boolean
}