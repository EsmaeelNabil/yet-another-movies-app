package com.esmaeel.challenge.ui.movieDetails

import com.esmaeel.challenge.R
import com.esmaeel.challenge.ui.moviesDetails.MovieDetailsActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView


/**
 * consider this as a representation for our screen and addons/helper/extension functions
 * that helps us with testing it
 * find more about how to use this approach here : https://github.com/KakaoCup/Kakao
 */
class MoviesDetailsScreen() : KScreen<MoviesDetailsScreen>() {
    override val layoutId: Int = R.layout.movies_list_activity
    override val viewClass: Class<*> = MovieDetailsActivity::class.java

    val information = KTextView { withId(R.id.information) }

}


