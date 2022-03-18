package com.esmaeel.challenge.data.repositories

import com.esmaeel.challenge.common.base.BaseRepository
import com.esmaeel.challenge.data.remote.RemoteDataSource
import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.data.remote.models.PaginatedListResponse
import com.esmaeel.challenge.di.ContextProvider
import com.esmaeel.challenge.di.ResourcesHandler
import com.esmaeel.challenge.domain.repositories.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    contextProvider: ContextProvider,
    resourcesHandler: ResourcesHandler
) : BaseRepository(contextProvider, resourcesHandler), IMoviesRepository {

    override fun getPopularMovies(): Flow<PaginatedListResponse<Movie>> = networkHandler {
        remoteDataSource.getPopularMovies()
    }

}