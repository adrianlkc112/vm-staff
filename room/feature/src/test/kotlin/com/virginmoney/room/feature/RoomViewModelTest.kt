package com.virginmoney.people.feature

import app.cash.turbine.test
import com.virginmoney.network.Response
import com.virginmoney.room.data.Room
import com.virginmoney.room.data.RoomRepo
import com.virginmoney.room.feature.ErrorRoomUiState
import com.virginmoney.room.feature.LoadedRoomUiState
import com.virginmoney.room.feature.LoadingRoomUiState
import com.virginmoney.room.feature.RoomViewModel
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

class RoomViewModelTest {
    @MockK
    lateinit var roomRepo: RoomRepo

    @get:Rule
    val rule = TestDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    @Test
    fun `RoomViewModel uiState - when init - should be LoadingRoomUiState`() {
        val viewModel = getViewModel()
        assertEquals(LoadingRoomUiState, viewModel.uiState.value)
    }

    @Test
    fun `RoomViewModel uiState - when api call success - should be LoadedRoomUiState`() {
        coEvery { roomRepo.getRooms() } returns Response.Success(Room.createMocks())

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadedRoomUiState)

                val loadedUiState = viewModel.uiState.value as LoadedRoomUiState
                assertEquals(1, loadedUiState.rooms.first().id)
            }
        }
    }

    @Test
    fun `RoomViewModel uiState - when api call error - should be ErrorRoomUiState`() {
        coEvery { roomRepo.getRooms() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorRoomUiState)
            }
        }
    }

    @Test
    fun `getRoomDetails() - when retry with success - should change to LoadedRoomUiState`() {
        coEvery { roomRepo.getRooms() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorRoomUiState)
            }
        }

        coEvery { roomRepo.getRooms() } coAnswers {
            delay(100)
            Response.Success(Room.createMocks())
        }
        runTest {
            viewModel.getRooms(true)
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadingRoomUiState)
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadedRoomUiState)

                val loadedUiState = viewModel.uiState.value as LoadedRoomUiState
                assertEquals(1, loadedUiState.rooms.first().id)
            }
        }
    }

    @Test
    fun `getRoomDetails() - when retry with failure again - should remain ErrorRoomUiState`() {
        coEvery { roomRepo.getRooms() } returns Response.Failure(Exception("Test"))

        val viewModel = getViewModel()
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorRoomUiState)
            }
        }

        coEvery { roomRepo.getRooms() } coAnswers {
            delay(100)
            Response.Failure(Exception("Test"))
        }
        viewModel.getRooms(true)
        runTest {
            viewModel.uiState.drop(1).test {
                awaitItem()
                assertTrue(viewModel.uiState.value is LoadingRoomUiState)
                awaitItem()
                assertTrue(viewModel.uiState.value is ErrorRoomUiState)
            }
        }
    }

    private fun getViewModel(): RoomViewModel = RoomViewModel(roomRepo)
}
