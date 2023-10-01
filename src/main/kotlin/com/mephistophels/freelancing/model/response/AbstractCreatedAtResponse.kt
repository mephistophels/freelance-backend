package com.mephistophels.freelancing.model.response

import java.time.LocalDateTime

abstract class AbstractCreatedAtResponse(
    id: Long,
    val createdAt: LocalDateTime
): AbstractResponse(id)