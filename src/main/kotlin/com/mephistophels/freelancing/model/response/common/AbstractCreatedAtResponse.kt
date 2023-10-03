package com.mephistophels.freelancing.model.response.common

import java.time.LocalDateTime

abstract class AbstractCreatedAtResponse(
    id: Long,
    val createdAt: LocalDateTime
): AbstractResponse(id)