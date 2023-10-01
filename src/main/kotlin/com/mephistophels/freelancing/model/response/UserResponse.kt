package com.mephistophels.freelancing.model.response

import com.mephistophels.freelancing.util.EMAIL_REGEX
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

class UserResponse(
    id: Long,
    createdAt: LocalDateTime,
    val email: String,
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    val birthday: LocalDate? = null,
): AbstractCreatedAtResponse(id, createdAt)