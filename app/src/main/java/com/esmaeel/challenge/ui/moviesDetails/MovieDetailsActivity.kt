package com.esmaeel.challenge.ui.moviesDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.databinding.MovieDetailsActivityBinding
import com.esmaeel.challenge.utils.ktx.getFormattedApiDate
import com.skydoves.bundler.bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    companion object {
        const val MOVIE = "Movie"
    }

    private val movie: Movie? by bundle(MOVIE)
    private val binder: MovieDetailsActivityBinding by lazy {
        MovieDetailsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        binder.back.setOnClickListener { onBackPressed() }
        movie?.let { renderMovie(it) }
    }

    private fun renderMovie(movie: Movie) = with(binder) {
        includedItem.title.text = movie.title
        includedItem.releaseDate.text = movie.releaseDate.getFormattedApiDate()
        includedItem.rating.text = movie.getVote().toString()
        includedItem.image.load(movie.getPosterImage())
        information.text = movie.overview
    }

}