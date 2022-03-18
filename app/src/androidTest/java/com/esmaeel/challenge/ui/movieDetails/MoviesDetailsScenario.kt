package com.esmaeel.challenge.ui.movieDetails

import androidx.core.os.bundleOf
import com.esmaeel.challenge.testUtils.goto
import com.esmaeel.challenge.testUtils.movie
import com.esmaeel.challenge.ui.moviesDetails.MovieDetailsActivity
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

/**
 * this is the ui test it self
 * this time we are testing when the data is provided throw intent
 * one item in it
 *
 * fromUserFlow = used to know if this scenario is running stand alone or not
 */
class MoviesDetailsScenario(fromUserFlow: Boolean = false) : Scenario() {

    val screen = MoviesDetailsScreen()
    private val emptyBundle = bundleOf(MovieDetailsActivity.MOVIE to movie)

    override val steps: TestContext<Unit>.() -> Unit = {
        if (fromUserFlow.not())
            step("open details screen") {
                goto(MovieDetailsActivity::class.java, emptyBundle)
            }
        screen {
            step("assert that the information has been passed") {
                information {
                    isVisible()
                    hasAnyText()
                }
            }
        }
    }

}