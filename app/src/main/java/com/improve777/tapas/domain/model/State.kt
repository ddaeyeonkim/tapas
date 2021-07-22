package com.improve777.tapas.domain.model

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(
        val status: Int,
        val message: String,
        val error: Throwable? = null,
        ) : State<Nothing>() {
            companion object {
                const val STATUS_JSON_ERROR = -1
                const val STATUS_EMPTY = 0
                val EMPTY = Error(STATUS_EMPTY, "empty")
            }
        }
    object Loading : State<Nothing>()

    inline fun <R> map(block: (T) -> R): State<R> {
        return when (this) {
            is Success -> Success(block(this.data))
            is Error -> this
            else -> Loading
        }
    }
}