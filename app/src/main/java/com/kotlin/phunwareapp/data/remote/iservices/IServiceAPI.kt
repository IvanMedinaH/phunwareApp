package com.kotlin.phunwareapp.data.remote.iservices

import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IServiceAPI {
    @GET("dev-interview-homework/master/feed.json")
    suspend fun getAllEvents(): List<StarWarsEventItem>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: Int): StarWarsEventItem?

    @POST("events")
    suspend fun saveEvent(@Body event: StarWarsEventItem): Response<Void>

    @DELETE("events/{id}")
    suspend fun deleteEvent(@Path("id") id: Int): Response<Void>
}