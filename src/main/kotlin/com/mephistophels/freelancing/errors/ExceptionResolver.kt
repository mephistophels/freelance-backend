package com.mephistophels.freelancing.errors


import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import com.fasterxml.jackson.databind.ObjectMapper

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class ExceptionResolver {

    @ExceptionHandler(ApiError::class)
    fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: Exception,
    ) {
        val exceptionToResponse = if (exception is ApiError) exception else ApiError(exception.message.orEmpty())
        val objectMapper = ObjectMapper().findAndRegisterModules()
        response.contentType = MediaType.APPLICATION_JSON.toString()
        response.status = exceptionToResponse.status.value()
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(exceptionToResponse))
    }
}
