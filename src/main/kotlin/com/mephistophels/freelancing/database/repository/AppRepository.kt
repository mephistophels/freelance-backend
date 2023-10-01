package com.mephistophels.freelancing.database.repository

import com.mephistophels.freelancing.database.entity.AbstractEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface AppRepository<T : AbstractEntity> : CrudRepository<T, Long>