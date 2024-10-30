package com.kotlin.phunwareapp.data.utils

sealed class ResultAPI<out T> {
    data class Success<out T>(val data: T?) : ResultAPI<T>()
    data class Error(val message: String) : ResultAPI<Nothing>()
    object Loading : ResultAPI<Nothing>()
}

