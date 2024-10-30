package com.kotlin.phunwareapp.data.implrepository.master

import com.kotlin.phunwareapp.data.remote.iservices.IServiceAPI
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.domain.repository.master.IStarwarsMasterRepository

    class StarWarsEventMasterImpl (private val apiCall : IServiceAPI): IStarwarsMasterRepository {
        override suspend fun getAllEvents(): ResultAPI<List<StarWarsEventItem>> {
            return  try {
                ResultAPI.Success( apiCall.getAllEvents() )
            } catch (e:Exception) {
                ResultAPI.Error(e.message.toString())
            }
        }
    }