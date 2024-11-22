package com.virginmoney.room.data

import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit

class RoomModuleTest {
    @Test
    fun `provideRoomService - should return a RoomService instance`() {
        val retrofit = Retrofit.Builder().baseUrl("https://example.com/").build()

        val result = RoomModule.provideRoomService(retrofit)

        assertTrue(result is RoomService)
    }
}
