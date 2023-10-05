package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.request.MarkRequest
import com.mephistophels.freelancing.model.response.MarkResponse
import com.mephistophels.freelancing.model.response.UserMarkResponse

interface MarkService {
    fun getUserMark(userId: Long): UserMarkResponse

    fun createMark(markRequest: MarkRequest): MarkResponse
}