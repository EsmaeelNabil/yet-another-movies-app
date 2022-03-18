package com.esmaeel.challenge.ui.moviesDetails

import com.esmaeel.challenge.common.base.BaseViewModel
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.di.ContextProvider
import com.esmaeel.challenge.domain.usecases.GetMoviesUseCase
import com.esmaeel.challenge.ui.moviesList.MoviesListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    contextProvider: ContextProvider
) : BaseViewModel(contextProvider) {

    fun getMoviesDetails() = launchBlock {
        getMoviesUseCase.invoke().collect {
            setState(
                if (it.results.isEmpty())
                    ViewState.Empty
                else
                    MoviesListViewState.OnMoviesListResponse(it.results)
            )
        }
    }

}