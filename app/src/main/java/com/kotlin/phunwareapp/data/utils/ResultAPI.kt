package com.kotlin.phunwareapp.data.utils

sealed class ResultAPI<out T> {
    data class Success<out T>(val data: T?) : ResultAPI<T>()
    sealed class Error : ResultAPI<Nothing>() {
        data class NetworkError(val message: String) : Error()
        data class ServerError(val message: String) : Error()
        object NoConnection : Error()
    }
    object Loading : ResultAPI<Nothing>()
}

