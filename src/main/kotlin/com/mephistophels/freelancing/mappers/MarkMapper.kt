package com.mephistophels.freelancing.mappers

import com.mephistophels.freelancing.model.response.UserMarkResponse
import org.springframework.stereotype.Component

@Component
class MarkMapper {
    fun asUserMarkResponse(mark: Double) = UserMarkResponse(mark = mark)
}