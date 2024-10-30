package com.kotlin.phunwareapp.domain.repository

import com.kotlin.phunwareapp.domain.model.StarWarsEventItem

interface IStarWarsEventRepository {
    suspend fun getAllEvents(): List<StarWarsEventItem>
    suspend fun getEventById(id: Int): StarWarsEventItem?
    suspend fun saveEvent(event: StarWarsEventItem): Boolean
    suspend fun deleteEventById(id: Int): Boolean
}
