package com.example.toptracertest.network.data


sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class GeneralError(val errorMessage: String) : NetworkResponse<Nothing>()
}

fun <T> T.asSuccessfulNetworkResponse(): NetworkResponse.Success<T> = NetworkResponse.Success(this)