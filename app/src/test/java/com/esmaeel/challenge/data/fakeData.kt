package com.esmaeel.challenge.data

import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.data.remote.models.PaginatedListResponse

val response = PaginatedListResponse(
    page = 0,
    totalResults = 0,
    totalPages = 0,
    results = listOf(
        Movie(
            id = 0,
            imdbId = null,
            overview = null,
            title = "",
            poster_path = "",
            releaseDate = null,
            vote_average = null
        )
    )
)

val emptyResponse =
    PaginatedListResponse<Movie>(results = listOf(), page = 1, totalPages = 2, totalResults = 2)
