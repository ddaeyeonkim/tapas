package com.improve777.tapas.ui.models

sealed class SeriesVo {
    data class Series(
        val index: Int,
        val id: Int,
        val title: String,
        val thumbnailUrl: String,
        val genre: String,
        val likeCount: Int,
        val isBookCover: Boolean,
    ) : SeriesVo()

    object Progress : SeriesVo()
}