package com.esmaeel.challenge.ui.moviesList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.esmaeel.challenge.data.remote.models.Movie
import com.esmaeel.challenge.databinding.ViewholderMovieItemBinding
import com.esmaeel.challenge.utils.ktx.getFormattedApiDate
import com.esmaeel.challenge.utils.ktx.layoutInflator


class MoviesListAdapter(
    private val onMovieClicked: (movie: Movie) -> Unit
) : ListAdapter<Movie, MoviesListAdapter.MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ViewholderMovieItemBinding.inflate(
                parent.layoutInflator,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binder: ViewholderMovieItemBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(movie: Movie) = with(binder) {
            binder.root.setOnClickListener { onMovieClicked(movie) }
            title.text = movie.title
            releaseDate.text = movie.releaseDate.getFormattedApiDate()
            rating.text = movie.getVote().toString()
            binder.image.load(movie.getPosterImage())
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
