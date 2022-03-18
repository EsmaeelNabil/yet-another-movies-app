package com.esmaeel.challenge.domain.usecases

import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.data.remote.models.PaginatedListResponse
import com.esmaeel.challenge.domain.repositories.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCase @Inject constructor(private val repository: IMoviesRepository) :
    UseCase<Flow<PaginatedListResponse<Movie>>> {

    override suspend fun invoke(): Flow<PaginatedListResponse<Movie>> {
        return repository.getPopularMovies()
    }
}