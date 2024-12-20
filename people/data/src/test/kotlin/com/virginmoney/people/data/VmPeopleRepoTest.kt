package com.virginmoney.people.data

import com.virginmoney.network.Response
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VmPeopleRepoTest {
    @MockK
    lateinit var peopleService: PeopleService

    private lateinit var vmPeopleRepo: VmPeopleRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        vmPeopleRepo = VmPeopleRepo(peopleService)
    }

    @Test
    fun `getPeopleDetails() - when api success - should return Success with non duplicate and sorted People data`() {
        coEvery {
            peopleService.getPeopleDetails()
        } returns People.createMocks()

        val result =
            runBlocking {
                vmPeopleRepo.getPeopleDetails()
            }

        assertTrue(result is Response.Success)
        val successResult = result as Response.Success
        assertEquals(3, successResult.data.size)
        assertEquals("John", successResult.data[0].firstName)
        assertEquals("Doe", successResult.data[0].lastName)
        assertEquals(1, successResult.data[0].id)
        assertEquals("john.doe@example.com", successResult.data[0].email)
        assertEquals("https://example.com/avatar1.jpg", successResult.data[0].avatar)
        assertEquals("Software Engineer", successResult.data[0].jobTitle)
        assertEquals("Blue", successResult.data[0].favouriteColor)

        val createdAt = successResult.data[0].createdAt
        assertEquals(2022, createdAt.year)
        assertEquals(1, createdAt.monthValue)
        assertEquals(24, createdAt.dayOfMonth)
        assertEquals(17, createdAt.hour)
        assertEquals(2, createdAt.minute)
        assertEquals(23, createdAt.second)

        assertEquals("June", successResult.data[1].firstName)
        assertEquals("William", successResult.data[2].firstName)
    }

    @Test
    fun `getPeopleDetails() - when api fail - should return Failure with Exception`() {
        coEvery {
            peopleService.getPeopleDetails()
        } throws Exception("Test")

        val result =
            runBlocking {
                vmPeopleRepo.getPeopleDetails()
            }

        assertTrue(result is Response.Failure)
        val failureResult = result as Response.Failure
        assertEquals("Test", failureResult.exception.message)
    }

    @Test
    fun `getPeopleDetails() - when api 1st success 2nd fail - on 2nd should return Success by using cached data`() {
        coEvery {
            peopleService.getPeopleDetails()
        } returns People.createMocks()

        val firstResult =
            runBlocking {
                vmPeopleRepo.getPeopleDetails()
            }

        assertTrue(firstResult is Response.Success)

        coEvery {
            peopleService.getPeopleDetails()
        } throws Exception("Test")

        val secondResult =
            runBlocking {
                vmPeopleRepo.getPeopleDetails()
            }

        assertTrue(secondResult is Response.Success)
        val successResult = secondResult as Response.Success
        assertEquals(3, successResult.data.size)
        assertEquals("John", successResult.data[0].firstName)
        assertEquals("June", successResult.data[1].firstName)
        assertEquals("William", successResult.data[2].firstName)
    }
}
