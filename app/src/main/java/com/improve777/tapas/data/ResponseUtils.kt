package com.improve777.tapas.data

import com.google.gson.JsonSyntaxException
import com.improve777.tapas.State
import retrofit2.Response

suspend fun <T> mapResponseToState(
    action: suspend () -> Response<T>,
): State<T> {
    return try {
        val response = action()
        if (response.isSuccessful) {
            State.Success(response.body()!!)
        } else {
            State.Error(response.code(), response.message())
        }
    } catch (e: JsonSyntaxException) {
        State.Error(-1, "begin object", e)
    } catch (e: Exception) {
        State.Error(404, "network error", e)
    }
}