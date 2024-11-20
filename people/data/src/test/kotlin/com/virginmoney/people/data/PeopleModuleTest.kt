package com.virginmoney.people.data

import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit

class PeopleModuleTest {
    @Test
    fun `providePeopleService - should return a PeopleService instance`() {
        val retrofit = Retrofit.Builder().baseUrl("https://example.com/").build()

        val result = PeopleModule.providePeopleService(retrofit)

        assertTrue(result is PeopleService)
    }
}
