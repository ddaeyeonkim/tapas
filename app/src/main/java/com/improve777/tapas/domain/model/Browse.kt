package com.improve777.tapas.domain.model

data class Browse(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val genre: String,
    val likeCount: Int,
    val isBookCover: Boolean,
)