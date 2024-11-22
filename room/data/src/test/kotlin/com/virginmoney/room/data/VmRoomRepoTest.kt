package com.virginmoney.room.data

import com.virginmoney.network.Response
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VmRoomRepoTest {
    @MockK
    lateinit var roomService: RoomService

    private lateinit var vmPeopleRepo: VmRoomRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        vmPeopleRepo = VmRoomRepo(roomService)
    }

    @Test
    fun `getRooms() - when api success - should return Success sorted Room data`() {
        coEvery {
            roomService.getRooms()
        } returns Room.createMocks()

        val result =
            runBlocking {
                vmPeopleRepo.getRooms()
            }

        assertTrue(result is Response.Success)
        val successResult = result as Response.Success
        assertEquals(1, successResult.data[0].id)
        assertEquals(false, successResult.data[0].isOccupied)
        assertEquals(84245L, successResult.data[0].maxOccupancy)

        assertEquals(2, successResult.data[1].id)
        assertEquals(3, successResult.data[2].id)
    }

    @Test
    fun `getRooms - when api fail - should return Failure with Exception`() {
        coEvery {
            roomService.getRooms()
        } throws Exception("Test")

        val result =
            runBlocking {
                vmPeopleRepo.getRooms()
            }

        assertTrue(result is Response.Failure)
        val failureResult = result as Response.Failure
        assertEquals("Test", failureResult.exception.message)
    }
}
