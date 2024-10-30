package com.kotlin.phunwareapp.presentation.starwars.master

import androidx.lifecycle.ViewModel
import com.kotlin.phunwareapp.data.local.NetworkVerifier

class StarWarsMasterViewModel(private val networkVerifier: NetworkVerifier): ViewModel() {

    fun checkNetwork() {
        if (networkVerifier.isNetworkAvailable()) {
            // La red está disponible
        } else {
            // La red no está disponible
        }
    }
}