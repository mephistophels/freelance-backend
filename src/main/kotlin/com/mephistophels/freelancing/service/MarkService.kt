package com.mephistophels.freelancing.service

import com.mephistophels.freelancing.database.entity.User
import com.mephistophels.freelancing.model.response.UserMarkResponse

interface MarkService {
    fun getUserMark(user: User): UserMarkResponse
}