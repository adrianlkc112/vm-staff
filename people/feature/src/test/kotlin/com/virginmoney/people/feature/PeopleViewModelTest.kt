package com.virginmoney.people.feature

import app.cash.turbine.test
import com.virginmoney.network.Response
import com.virginmoney.people.data.People
import com.virginmoney.people.data.PeopleRepo
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class PeopleViewModelTest {
    @MockK
    lateinit var peopleRepo: PeopleRepo

    @get:Rule
    val rule = TestDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    @Test
    fun `PeopleViewModel uiState - when init - should be LoadingPeopleUiState`() {
        val viewModel = getViewModel()
        assertEquals(LoadingPeopleUiState, viewModel.uiState.value)
    }

    @Test
    fun `PeopleViewModel uiState - when api call success - should be LoadedPeopleUiState`() {
        coEvery { peopleRepo.getPeopleDetails() } returns Response.Success(People.createMocks())

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadedPeopleUiState)

                val loadedUiState = viewModel.uiState.value as LoadedPeopleUiState
                assertEquals("John", loadedUiState.peoples.first().firstName)
            }
        }
    }

    @Test
    fun `PeopleViewModel uiState - when api call error - should be ErrorPeopleUiState`() {
        coEvery { peopleRepo.getPeopleDetails() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorPeopleUiState)
            }
        }
    }

    @Test
    fun `getPeopleDetails() - when retry with success - should change to LoadedPeopleUiState`() {
        coEvery { peopleRepo.getPeopleDetails() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorPeopleUiState)
            }
        }

        coEvery { peopleRepo.getPeopleDetails() } coAnswers {
            delay(100)
            Response.Success(People.createMocks())
        }
        runTest {
            viewModel.getPeopleDetails(true)
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadingPeopleUiState)
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadedPeopleUiState)

                val loadedUiState = viewModel.uiState.value as LoadedPeopleUiState
                assertEquals("John", loadedUiState.peoples.first().firstName)
            }
        }
    }

    @Test
    fun `getPeopleDetails() - when retry with failure again - should remain ErrorPeopleUiState`() {
        coEvery { peopleRepo.getPeopleDetails() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorPeopleUiState)
            }
        }

        coEvery { peopleRepo.getPeopleDetails() } coAnswers {
            delay(100)
            Response.Failure(Exception("Test"))
        }
        viewModel.getPeopleDetails(true)
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadingPeopleUiState)
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorPeopleUiState)
            }
        }
    }

    private fun getViewModel(): PeopleViewModel = PeopleViewModel(peopleRepo)
}
