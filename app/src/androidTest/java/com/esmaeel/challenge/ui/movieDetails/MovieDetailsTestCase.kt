package com.esmaeel.challenge.ui.movieDetails

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

/**
 *  so this is the entry point of our UI-TEST which defines what test cases we have on this feature
 *  every test case might have different scenarios and that is why we need a scenario to run.
 */
class MovieDetailsTestCase : TestCase() {

    @Test
    fun screenDataScenario() = run {
        // we can test for if the response has empty list in another scenario
        step("open details for a movie that have information") {
            scenario(MoviesDetailsScenario())
        }
    }
}