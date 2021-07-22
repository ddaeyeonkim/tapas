package com.improve777.tapas.ui.models

sealed class EpisodeVo {
    data class Episode(
        val id: Int,
        val title: String,
        val scene: Int,
        val createdDate: String,
        val thumbnailUrl: String,
        val hadRead: Boolean,
    ) : EpisodeVo()

    data class SectionName(val name: String) : EpisodeVo()
}