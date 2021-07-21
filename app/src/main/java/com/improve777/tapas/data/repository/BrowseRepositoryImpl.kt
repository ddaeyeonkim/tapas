package com.improve777.tapas.data.repository

import com.improve777.tapas.State
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.repository.BrowseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrowseRepositoryImpl : BrowseRepository {
    override fun getBrowseList(page: Int): Flow<State<List<Browse>>> {
        return flow {
            delay(3000)

            val items = listOf(
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "Romance", likeCount = 1, isBookCover = false),
                Browse(id = 0, title = "", thumbnailUrl = "", genre = "", likeCount = 100, isBookCover = true),
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
}