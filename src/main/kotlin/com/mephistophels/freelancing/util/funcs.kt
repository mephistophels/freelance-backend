package com.mephistophels.freelancing.util

import com.mephistophels.freelancing.security.model.UserPrincipal
import org.springframework.security.core.context.SecurityContextHolder

// returns user id
fun getPrincipal(): Long = (SecurityContextHolder.getContext().authentication.principal as Long)
