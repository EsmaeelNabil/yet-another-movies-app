package com.esmaeel.challenge.domain.repositories

import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.data.remote.models.PaginatedListResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getPopularMovies(): Flow<PaginatedListResponse<Movie>>
}