package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.Message
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageDao : AppRepository<Message>