package com.kotlin.phunwareapp.domain.repository.detail

import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem

interface IStarwarsDetailRepository {
    suspend fun getEventById(id: Int): ResultAPI<StarWarsEventItem?>
}