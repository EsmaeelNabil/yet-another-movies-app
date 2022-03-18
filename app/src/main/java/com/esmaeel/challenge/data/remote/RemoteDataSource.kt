package com.esmaeel.challenge.data.remote

import javax.inject.Inject
import javax.inject.Singleton

/**
 * this is responsible for getting movies from the network
 */
@Singleton
class RemoteDataSource @Inject constructor(private val remoteService: MoviesService) {
    suspend fun getPopularMovies() = remoteService.getPopularMovies()
}

