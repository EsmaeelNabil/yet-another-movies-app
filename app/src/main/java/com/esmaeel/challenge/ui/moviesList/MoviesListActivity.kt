package com.esmaeel.challenge.ui.moviesList

import androidx.activity.viewModels
import com.esmaeel.challenge.R
import com.esmaeel.challenge.common.base.BaseActivity
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.databinding.MoviesListActivityBinding
import com.esmaeel.challenge.ui.moviesDetails.MovieDetailsActivity
import com.esmaeel.challenge.utils.ktx.show
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesListActivity :
    BaseActivity<MoviesListActivityBinding, MoviesListViewModel>(R.layout.movies_list_activity) {

    private fun openMovieDetails(movie: Movie) {
        intentOf<MovieDetailsActivity> {
            putExtra(MovieDetailsActivity.MOVIE to movie)
            startActivity(this@MoviesListActivity)
        }
    }

    override val viewModel: MoviesListViewModel by viewModels()

    private val listAdapter by lazy {
        MoviesListAdapter(
            onMovieClicked = { movie -> openMovieDetails(movie) }
        )
    }


    override fun setup() = with(binder) {
        recycler.adapter = listAdapter
        refreshButton.setOnClickListener { getData() }
        swipeToRefresh.setOnRefreshListener {
            swipeToRefresh.isRefreshing = false
            getData()
        }
        getData()
    }

    private fun getData() {
        viewModel.getMoviesList()
    }

    override fun render(state: ViewState) {
        when (state) {
            is MoviesListViewState.OnMoviesListResponse -> bindList(state.data)
            is ViewState.Error -> handleError(state.error)
            is ViewState.AuthRequired -> {
                showError("Auth error")
                hideLoading()
            }
            is ViewState.Empty -> {
                /*in case of empty lists*/
            }
        }
    }

    private fun bindList(data: List<Movie>) {
        binder.recycler.show()
        listAdapter.submitList(data)
    }

    private fun handleError(error: String?) {
        error?.let { showError(it) }
        hideLoading()
    }


}

