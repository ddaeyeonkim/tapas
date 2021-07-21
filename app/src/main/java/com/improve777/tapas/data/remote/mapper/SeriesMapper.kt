package com.improve777.tapas.data.remote.mapper

import com.improve777.tapas.data.remote.model.BrowseResponse
import com.improve777.tapas.data.remote.model.EpisodeResponse
import com.improve777.tapas.data.remote.model.PaginationResponse
import com.improve777.tapas.data.remote.model.SeriesInfoResponse
import com.improve777.tapas.data.remote.model.SeriesResponse
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Episode
import com.improve777.tapas.domain.model.Pagination
import com.improve777.tapas.domain.model.Series
import com.improve777.tapas.domain.model.SeriesInfo

fun BrowseResponse.toDomain() = Browse(
    pagination = pagination?.toDomain() ?: Pagination(1, false),
    seriesList = series.map(SeriesResponse::toDomain)
)

fun PaginationResponse.toDomain() = Pagination(
    page = page ?: 0,
    hasNext = hasNext ?: false
)

fun SeriesResponse.toDomain() = Series(
    id = id ?: 0,
    title = title ?: "",
    thumbnailUrl = bookCoverUrl ?: (thumb?.fileUrl ?: ""),
    genre = genre?.name ?: "",
    likeCount = likeCnt ?: 0,
    isBookCover = bookCoverUrl != null
)

fun SeriesInfoResponse.toDomain() = SeriesInfo(
    id = id ?: 0,
    title = title ?: "",
    thumbnailUrl = bookCoverUrl ?: (thumb?.fileUrl ?: ""),
    creator = creators?.foldRight("") { creator, acc ->
        "${creator.displayName}${if (acc.isNotBlank()) ", $acc" else acc}"
    } ?: "",
    isBookCover = bookCoverUrl != null,
)

fun EpisodeResponse.toDomain() = Episode(
    id = id ?: 0,
    title = title ?: "",
    scene = scene ?: 0,
    createdDate = createdDate ?: "",
    thumbnailUrl = thumb?.fileUrl ?: "",
    hadRead = read ?: false,
)