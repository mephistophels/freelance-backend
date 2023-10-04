package com.mephistophels.freelancing.service.impl

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.database.repository.MarkDao
import com.mephistophels.freelancing.mappers.MarkMapper
import com.mephistophels.freelancing.model.response.UserMarkResponse
import com.mephistophels.freelancing.service.MarkService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class MarkServiceImpl(
    private val mapper: MarkMapper,
    private val dao: MarkDao
) : MarkService {

    override fun getUserMark(user: User): UserMarkResponse {
        // TODO
        val mark = 0.0  //dao.getUserMiddleMark(user.id)
        return mapper.asUserMarkResponse(mark)
    }
}