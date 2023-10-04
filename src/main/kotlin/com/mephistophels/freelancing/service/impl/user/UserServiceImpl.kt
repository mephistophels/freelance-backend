package com.mephistophels.freelancing.service.impl.user

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.database.repository.UserDao
import com.mephistophels.freelancing.errors.AlreadyExistsException
import com.mephistophels.freelancing.errors.ResourceNotFoundException
import com.mephistophels.freelancing.mappers.UserMapper
import com.mephistophels.freelancing.model.request.RegistrationRequest
import com.mephistophels.freelancing.model.response.user.UserFullResponse
import com.mephistophels.freelancing.model.response.user.UserMediumResponse
import com.mephistophels.freelancing.service.BalanceService
import com.mephistophels.freelancing.service.MarkService
import com.mephistophels.freelancing.service.UserService
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val mapper: UserMapper,
    private val dao: UserDao,
    @Lazy private val balanceService: BalanceService,
    @Lazy private val markService: MarkService,
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

    override fun getSelfProfile(userId: Long): UserFullResponse {
        val user = findEntityById(userId)
        val balance = balanceService.getBalanceAmount(user)
        val mark = markService.getUserMark(user)
        return mapper.asUserFullResponse(user, balance, mark)
    }

    override fun getUserProfile(userId: Long): UserMediumResponse {
        val user = findEntityById(userId)
        val mark = markService.getUserMark(user)
        return mapper.asUserMediumResponse(user, mark)
    }

    override fun createUser(request: RegistrationRequest): User {
        if (dao.existsByEmail(request.email)) {
            throw AlreadyExistsException(request.email)
        }
        val user = mapper.asEntity(request).apply {
            hash = passwordEncoder.encode(request.password)
            dao.save(this)
        }
        return user
    }

}