package com.improve777.tapas.data.remote.model

import com.google.gson.annotations.SerializedName

data class ThumbResponse(
    @SerializedName("width")
    val width: Int?, // 300
    @SerializedName("height")
    val height: Int?, // 300
    @SerializedName("file_size")
    val fileSize: Int?, // 53156
    @SerializedName("file_url")
    val fileUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/90/3887becb-a368-450f-b6c4-7b2efd5aeddf.jpg
)