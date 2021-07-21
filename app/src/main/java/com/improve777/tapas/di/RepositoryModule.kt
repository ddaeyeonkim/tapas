package com.improve777.tapas.di

import com.improve777.tapas.data.remote.api.SeriesApi
import com.improve777.tapas.data.repository.BrowseRepositoryImpl
import com.improve777.tapas.domain.repository.BrowseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideBrowseRepository(seriesApi: SeriesApi): BrowseRepository {
        return BrowseRepositoryImpl(seriesApi)
    }
}