package com.kotlin.phunwareapp.data.remote.iservices.starwarsservice.detail

import com.kotlin.phunwareapp.domain.model.StarWarsEventItem

interface IStarwarsDetail {
    suspend fun getEventById(id: Int): StarWarsEventItem?
}