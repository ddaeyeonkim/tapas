package com.improve777.tapas.domain.model

data class Episode(
    val id: Int,
    val title: String,
    val scene: Int,
    val createdDate: String,
    val thumbnailUrl: String,
    val hadRead: Boolean,
)