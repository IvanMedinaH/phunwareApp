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

class StarWarsMasterViewModel(
    private val networkVerifier: NetworkVerifier,
    private val starWarsMasterRepository: IStarwarsMasterRepository
) : ViewModel() {

    // LiveData para observar los resultados de eventos
    private val _events = MutableLiveData<ResultAPI<List<StarWarsEventItem>>>()
    val events: LiveData<ResultAPI<List<StarWarsEventItem>>> = _events



    fun checkNetwork() {
        if (networkVerifier.isNetworkAvailable()) {
            // La red está disponible, proceder a obtener eventos
            fetchStarWarsEvents()
        } else {
            // La red no está disponible
            _events.value = ResultAPI.Error("No network available")
        }
    }

     fun fetchStarWarsEvents() {
        viewModelScope.launch {
            _events.value = starWarsMasterRepository.getAllEvents()
        }
    }
}