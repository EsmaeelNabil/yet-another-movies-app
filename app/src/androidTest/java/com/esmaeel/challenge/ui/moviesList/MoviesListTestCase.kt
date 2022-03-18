package com.esmaeel.challenge.ui.moviesList

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

/**
 *  so this is the entry point of our UI-TEST which defines what test cases we have on this feature
 *  every test case might have different scenarios and that is why we need a scenario to run.
 */
class MoviesListTestCase : TestCase() {

    @Test
    fun listWithDataScenario() = run {
        // we can test for if the response has empty list in another scenario
        step("request list for a movies that have items") {
            scenario(MoviesListScenario())
        }
    }
}