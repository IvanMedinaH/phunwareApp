package com.kotlin.phunwareapp.data.remote.model

data class StarWarsEventItemDTO(
    val date: String?,
    val description: String,
    val id: Int,
    val image: String? = null, // Now nullable
    val locationline1: String,
    val locationline2: String,
    val phone: String,
    val timestamp: String?,
    val title: String,
)