package com.improve777.tapas.ui.mapper

import com.improve777.tapas.domain.model.Episode
import com.improve777.tapas.domain.model.Series
import com.improve777.tapas.ui.models.EpisodeVo
import com.improve777.tapas.ui.models.SeriesVo

fun Series.toVo(index: Int) = SeriesVo.Series(
    index = index,
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    genre = genre,
    likeCount = likeCount,
    isBookCover = isBookCover,
)

fun Episode.toVo() = EpisodeVo.Episode(
    id = id,
    title = title,
    scene = scene,
    createdDate = createdDate,
    thumbnailUrl = thumbnailUrl,
    hadRead = hadRead,
)