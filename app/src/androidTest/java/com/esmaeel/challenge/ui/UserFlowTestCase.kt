package com.esmaeel.challenge.ui

import com.esmaeel.challenge.ui.moviesList.MoviesListScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test


class UserFlowTestCase : TestCase() {

    @Test
    fun getListOfMoviesThenViewItsDetails() = run {
        step("open details for a movie that have information") {
            scenario(MoviesListScenario(openDetails = true))
        }
    }

}