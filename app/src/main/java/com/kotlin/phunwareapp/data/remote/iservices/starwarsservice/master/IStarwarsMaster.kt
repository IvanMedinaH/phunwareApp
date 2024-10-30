package com.kotlin.phunwareapp.data.remote.iservices.starwarsservice.master

import com.kotlin.phunwareapp.domain.model.StarWarsEventItem

interface IStarwarsMaster {
    suspend fun getAllEvents(): List<StarWarsEventItem>
}