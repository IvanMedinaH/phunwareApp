package com.kotlin.phunwareapp.presentation.starwars.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.phunwareapp.data.local.NetworkVerifier
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.domain.repository.master.IStarwarsMasterRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class StarWarsMasterViewModel(
    private val networkVerifier: NetworkVerifier,
    private val starWarsMasterRepository: IStarwarsMasterRepository
) : ViewModel() {

    // LiveData para observar los resultados de eventos
    private val _events = MutableLiveData<ResultAPI<List<StarWarsEventItem>>>()
    val events: LiveData<ResultAPI<List<StarWarsEventItem>>> = _events

    init {
        checkNetwork()
    }

    fun checkNetwork() {
        viewModelScope.launch {
            if (networkVerifier.isNetworkAvailable()) {
                fetchStarWarsEvents()
            } else {
                _events.value = ResultAPI.Error.NetworkError("No network available")
            }
        }
    }

     fun fetchStarWarsEvents() {
        viewModelScope.launch {
            try {
                val result = starWarsMasterRepository.getAllEvents()
                _events.value = result
            } catch (exception: Exception) {
                // Handle different types of exceptions
                when (exception) {
                    is IOException -> {
                        _events.value = ResultAPI.Error.NetworkError("Network error: ${exception.message}")
                    }
                    is HttpException -> {
                        _events.value = ResultAPI.Error.ServerError("Server error: ${exception.code()} - ${exception.message()}")
                    }
                    else -> {
                        _events.value = ResultAPI.Error.NoConnection
                    }
                }
            }
        }
    }

    // Obtener evento por ID
    fun getEventById(id: String): StarWarsEventItem? {
        val result = _events.value
        return if (result is ResultAPI.Success) {
            val events = result.data
            events?.find { it.id.toString() == id }
        } else {
            null
        }
    }

}