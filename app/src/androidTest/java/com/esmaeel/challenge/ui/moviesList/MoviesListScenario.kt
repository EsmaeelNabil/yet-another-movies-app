package com.esmaeel.challenge.ui.moviesList

import com.esmaeel.challenge.testUtils.goto
import com.esmaeel.challenge.ui.movieDetails.MoviesDetailsScenario
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import io.kotlintest.matchers.numerics.shouldBeGreaterThan

/**
 * this is the ui test it self
 * this time we are testing when the list is not empty and has at least
 * one item in it
 *
 * openDetails is for when we use this scenario in a user flow
 */
class MoviesListScenario(private val openDetails: Boolean = false) : Scenario() {

    val listScreen = MoviesListScreen()

    override val steps: TestContext<Unit>.() -> Unit = {
        listScreen {
            step("open activity") {
                goto(MoviesListActivity::class.java)
            }

            list {
                step("assert that the recycler view is visible") {
                    isVisible()
                }

                step("assert that the data is more than zero") {
                    flakySafely(4_000) {
                        getSize().shouldBeGreaterThan(0)
                    }
                }


                step("click on first item") {
                    flakySafely {
                        childAt<MoviesListScreen.Item>(0){
                            click()
                        }
                    }
                }

                if (openDetails) {
                    scenario(MoviesDetailsScenario(fromUserFlow = true))
                }
            }
        }
    }

}