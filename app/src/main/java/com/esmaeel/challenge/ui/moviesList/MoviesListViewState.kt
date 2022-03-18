package com.esmaeel.challenge.ui.moviesList

import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.Movie

sealed class MoviesListViewState : ViewState() {
    data class OnMoviesListResponse(val data: List<Movie>) :
        Loaded<List<Movie>>(data)
}