package com.improve777.tapas.data.repository

import com.improve777.tapas.domain.model.State
import com.improve777.tapas.data.mapResponseToState
import com.improve777.tapas.data.remote.api.SeriesApi
import com.improve777.tapas.data.remote.mapper.toDomain
import com.improve777.tapas.data.remote.model.BrowseResponse
import com.improve777.tapas.data.remote.model.EpisodeResponse
import com.improve777.tapas.data.remote.model.SeriesInfoResponse
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Episode
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.domain.repository.BrowseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BrowseRepositoryImpl(
    private val seriesApi: SeriesApi,
) : BrowseRepository {
    override fun getBrowse(page: Int): Flow<State<Browse>> {
        return flow {
            emit(State.Loading)
            emit(mapResponseToState { seriesApi.getBrowse(page = page) })
        }.map {
            it.map(BrowseResponse::toDomain)
        }.flowOn(Dispatchers.Default)
    }

    override fun getSeriesInfo(seriesId: Int): Flow<State<SeriesInfo>> {
        return flow {
            emit(State.Loading)
            emit(mapResponseToState { seriesApi.getSeriesInfo(seriesId) })
        }.map {
            it.map(SeriesInfoResponse::toDomain)
        }.flowOn(Dispatchers.Default)
    }

    override fun getEpisodeList(seriesId: Int): Flow<State<List<Episode>>> {
        return flow {
            emit(State.Loading)
            emit(mapResponseToState { seriesApi.getEpisodeList(seriesId) })
        }.map { state ->
            state.map { list ->
                list.map(EpisodeResponse::toDomain)
            }
        }.flowOn(Dispatchers.Default)
    }
}