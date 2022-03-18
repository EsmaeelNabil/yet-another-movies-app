package com.esmaeel.challenge.ui.moviesList

import app.cash.turbine.test
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.emptyResponse
import com.esmaeel.challenge.data.response
import com.esmaeel.challenge.domain.usecases.GetMoviesUseCase
import com.esmaeel.challenge.utils.TestContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest {

    private lateinit var viewModel: MoviesListViewModel
    private val contextProvidersTest = TestContextProvider()

    @MockK
    lateinit var useCase: GetMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MoviesListViewModel(
            getMoviesUseCase = useCase,
            contextProvider = contextProvidersTest,
        )
    }

    @Test
    fun `getMoviesList returns empty list of data`() = runBlockingTest {
        coEvery { useCase.invoke() } returns flowOf(emptyResponse)

        viewModel.getMoviesList()

        viewModel.state.test {
            val state = awaitItem()
            assertIs<ViewState.Empty>(state)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `getMoviesList returns list of data`() = runBlockingTest {
        coEvery { useCase.invoke() } returns flowOf(response)

        viewModel.getMoviesList()

        viewModel.state.test {
            val state = awaitItem()
            assertIs<MoviesListViewState.OnMoviesListResponse>(state)
            assertEquals(response.results, state.result)
            cancelAndIgnoreRemainingEvents()
        }

    }
}