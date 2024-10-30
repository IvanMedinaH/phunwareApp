package com.kotlin.phunwareapp.data.implrepository.detail

import com.kotlin.phunwareapp.data.remote.iservices.IServiceAPI
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.domain.repository.detail.IStarwarsDetailRepository
import com.kotlin.phunwareapp.domain.repository.master.IStarwarsMasterRepository

class StarWarsEventDetailImpl(private val apiCall : IServiceAPI) : IStarwarsDetailRepository {
    override suspend fun getEventById(id: Int): ResultAPI<StarWarsEventItem?> {
        return try {
            ResultAPI.Success(apiCall.getEventById(id))
        }catch (e:Exception){
            ResultAPI.Error(e.message.toString())
        }
    }
}