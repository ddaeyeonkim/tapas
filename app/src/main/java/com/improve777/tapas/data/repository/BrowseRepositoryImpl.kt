package com.improve777.tapas.data.repository

import com.improve777.tapas.State
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Episode
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.domain.repository.BrowseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrowseRepositoryImpl : BrowseRepository {
    override fun getBrowseList(page: Int): Flow<State<List<Browse>>> {
        return flow {
            emit(State.Loading)

            delay(300)

            val items = listOf(
                Browse(id = 0, title = "Love Lesson", thumbnailUrl = "https://d30womf5coomej.cloudfront.net/sa/90/3887becb-a368-450f-b6c4-7b2efd5aeddf.jpg", genre = "Romance", likeCount = 1, isBookCover = false),
                Browse(id = 0, title = "Love Lesson", thumbnailUrl = "https://d30womf5coomej.cloudfront.net/sa/96/85c6a6d3-3004-487e-88a0-c525d50e64db.jpg", genre = "", likeCount = 100, isBookCover = true),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 1000, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 1111, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 1800, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 12331800, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 0, isBookCover = true),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 0, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 0, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 0, isBookCover = false),
            )

            emit(State.Success(items))
        }
    }

    override fun getSeriesInfo(seriesId: Int): Flow<State<SeriesInfo>> {
        return flow {
            emit(State.Loading)

            delay(300)

            val seriesInfo = SeriesInfo(
                id = 0,
                title = "Love Lesson",
                thumbnailUrl = "https://d30womf5coomej.cloudfront.net/sa/90/3887becb-a368-450f-b6c4-7b2efd5aeddf.jpg",
                creator = "Kuaikan Comics",
                isBookCover = false,
            )

            emit(State.Success(seriesInfo))
        }
    }

    override fun getEpisodeList(seriesId: Int): Flow<State<List<Episode>>> {
        return flow {
            emit(State.Loading)

            delay(300)

            val items = listOf(
                Episode(id = 1, title = "Prologue", scene = 21, createdDate = "2021-02-16T18:18:13Z", thumbnailUrl = "https://d30womf5coomej.cloudfront.net/sa/8b/83b3c890-a21f-45ac-9366-bc382df56f9b.jpg", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
                Episode(id = 0, title = "", scene = 0, createdDate = "", thumbnailUrl = "", hadRead = true),
            )

            emit(State.Success(items))
        }
    }
}