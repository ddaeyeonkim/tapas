package com.improve777.tapas.domain.repository

import com.improve777.tapas.State
import com.improve777.tapas.domain.model.Browse
import kotlinx.coroutines.flow.Flow

interface BrowseRepository {
    fun getBrowseList(page: Int): Flow<State<List<Browse>>>
}