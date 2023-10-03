package com.mephistophels.freelancing.service.impl.user

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.database.repository.UserDao
import com.mephistophels.freelancing.errors.AlreadyExistsException
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.UserMapper
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userMapper: UserMapper,
    @Lazy
    private val dao: UserDao,
) : UserService {

    override fun existByEmail(email: String): Boolean {
        return dao.existsByEmail(email)
    }

    override fun findEntityByEmail(email: String) : User {
        return dao.findByEmail(email).orElseThrow { ResourceNotFoundException(email) }
    }

    override fun findEntityById(id: Long): User {
        return dao.findById(id).orElseThrow { ResourceNotFoundException(id) }
    }

    override fun createUser(request: RegistrationRequest): User {
        if (dao.existsByEmail(request.email)) {
            throw AlreadyExistsException(request.email)
        }
        val user = userMapper.asEntity(request).apply {
            hash = passwordEncoder.encode(request.password)
            dao.save(this)
        }
        return user
    }

}