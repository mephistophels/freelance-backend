package com.mephistophels.freelancing.security.model

import org.springframework.security.core.GrantedAuthority
import com.mephistophels.freelancing.util.*

enum class Authority(val authority: GrantedAuthority) {
    USER(GrantedAuthority { USER_ROLE })
}