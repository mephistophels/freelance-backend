package com.mephistophels.freelancing.util

import com.mephistophels.freelancing.security.model.UserPrincipal
import org.springframework.security.core.context.SecurityContextHolder

fun getPrincipal(): UserPrincipal = (SecurityContextHolder.getContext().authentication.principal as UserPrincipal)
