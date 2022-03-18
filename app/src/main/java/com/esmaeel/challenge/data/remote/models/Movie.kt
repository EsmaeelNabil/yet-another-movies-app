package com.esmaeel.challenge.data.remote.models

import com.esmaeel.challenge.BuildConfig
import com.squareup.moshi.Json
import java.io.Serializable
import java.util.Date

data class Movie(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    val poster_path: String,
    @Json(name = "release_date") val releaseDate: Date?,
    val vote_average: Float?
) : Serializable {
    fun getVote() = vote_average ?: 0
    fun getPosterImage() = "${BuildConfig.BASE_IMAGE}$poster_path"
}
