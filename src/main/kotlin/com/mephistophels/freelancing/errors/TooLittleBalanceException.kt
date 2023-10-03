package com.mephistophels.freelancing.errors

import org.springframework.http.HttpStatus

class TooLittleBalanceException(
    status: HttpStatus = HttpStatus.BAD_REQUEST,
): ApiError(status, message = "Недостаточно денег для совершения операции")