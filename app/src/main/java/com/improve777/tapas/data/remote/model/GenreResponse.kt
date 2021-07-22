package com.improve777.tapas.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int?, // 5
    @SerializedName("name")
    val name: String?, // Romance
    @SerializedName("abbr")
    val abbr: String?, // Romance
    @SerializedName("books")
    val books: Boolean?, // false
)