package com.improve777.tapas.ui.mapper

import com.improve777.tapas.domain.model.Series
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