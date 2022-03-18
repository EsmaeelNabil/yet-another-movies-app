package com.esmaeel.challenge.data.remote

import com.esmaeel.challenge.common.base.INJECT_API_KEY
import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.data.remote.models.PaginatedListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    companion object {
        const val EN = "en"
        const val MOVIES_LIST_API = "movie/popular"
        const val AUTHORIZATION = "api_key"
        const val Language = "language"
    }

    @INJECT_API_KEY
    @GET(MOVIES_LIST_API)
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): PaginatedListResponse<Movie>

}
