package com.kotlin.phunwareapp.domain.repository.master

import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem

interface IStarwarsMasterRepository {
    suspend fun getAllEvents(): ResultAPI<List<StarWarsEventItem>>
}