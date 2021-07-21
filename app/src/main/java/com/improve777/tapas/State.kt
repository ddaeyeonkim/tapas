package com.improve777.tapas

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val error: Throwable) : State<Nothing>()
    object Loading : State<Nothing>()
}