package com.kotlin.phunwareapp.data.implrepository.detail

import com.kotlin.phunwareapp.data.remote.iservices.IServiceAPI
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.domain.repository.detail.IStarwarsDetailRepository


class StarWarsEventDetailImpl(private val apiCall: IServiceAPI) : IStarwarsDetailRepository {

    private var cachedEvents: List<StarWarsEventItem>? = null

    override suspend fun getEventById(id: Int): ResultAPI<StarWarsEventItem> {
        val events = cachedEvents ?: apiCall.getAllEvents() // Fetch from API if not cached
        cachedEvents = events
        val event = events.find { it.id == id }
        return event?.let { ResultAPI.Success(it) } ?: ResultAPI.Error.ServerError("Event not found")
    }
}
