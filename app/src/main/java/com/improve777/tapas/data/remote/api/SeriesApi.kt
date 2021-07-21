package com.improve777.tapas.data.remote.api

import com.improve777.tapas.data.remote.model.BrowseResponse
import com.improve777.tapas.data.remote.model.EpisodeResponse
import com.improve777.tapas.data.remote.model.SeriesInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("browse/fresh")
    suspend fun getBrowse(
        @Query("series_type") seriesType: String = "COMICS",
        @Query("page") page: Int,
    ): Response<BrowseResponse>

    @GET("series/{seriesId}")
    suspend fun getSeriesInfo(
        @Path("seriesId") seriesId: Int,
    ): Response<SeriesInfoResponse>

    @GET("series/{seriesId}/episode")
    suspend fun getEpisodeList(
        @Path("seriesId") seriesId: Int,
    ): Response<List<EpisodeResponse>>
}