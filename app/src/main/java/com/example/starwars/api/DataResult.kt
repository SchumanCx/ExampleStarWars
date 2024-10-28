package com.example.starwars.api

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error<T>(val data: T, val exception: Throwable) : DataResult<Nothing>()

    object Loading : DataResult<Nothing>()

}
