package com.kotlin.phunwareapp.presentation.starwars.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.domain.repository.detail.IStarwarsDetailRepository
import kotlinx.coroutines.launch

class StarWarsDetailViewModel(
    private val repository: IStarwarsDetailRepository
) : ViewModel() {

    private val _eventDetail = MutableLiveData<ResultAPI<StarWarsEventItem>>()
    val eventDetail: LiveData<ResultAPI<StarWarsEventItem>> = _eventDetail

    fun fetchEventDetail(id: Int) {
        viewModelScope.launch {
            _eventDetail.value = repository.getEventById(id)
        }
    }
}