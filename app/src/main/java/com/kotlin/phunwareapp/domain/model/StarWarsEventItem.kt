package com.kotlin.phunwareapp.domain.model

data class StarWarsEventItem(
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