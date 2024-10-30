package com.kotlin.phunwareapp.presentation.starwars.detail

import androidx.lifecycle.ViewModel
import com.kotlin.phunwareapp.data.local.NetworkVerifier

class StarWarsDetailViewModel(private val networkVerifier: NetworkVerifier) :ViewModel() {
    fun checkNetwork() {
        if (networkVerifier.isNetworkAvailable()) {
            // La red está disponible
        } else {
            // La red no está disponible
        }
    }

}