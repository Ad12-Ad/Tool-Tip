package com.example.tooltip.core.network

sealed class NetworkResult <out T>{
    data class Success<T>(val data: T): NetworkResult<T>()
    data class ApiError(
        val code: Int,
        val errorMessage: String
    ): NetworkResult<Nothing>()
    data class NetworkError(val message: String): NetworkResult<Nothing>()
    data object UnknownError: NetworkResult<Nothing>()
}