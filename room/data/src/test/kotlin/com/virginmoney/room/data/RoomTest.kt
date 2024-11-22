package com.virginmoney.room.data

import com.squareup.moshi.Types
import com.virginmoney.network.NetworkModule_Companion_ProvideMoshiFactory
import org.intellij.lang.annotations.Language
import org.junit.Assert.assertEquals
import org.junit.Test

class RoomTest {
    @Test
    fun `Room - should deserialise fields correctly`() {
        val result =
            NetworkModule_Companion_ProvideMoshiFactory
                .provideMoshi()
                .adapter<List<Room>>(
                    Types.newParameterizedType(
                        List::class.java,
                        Room::class.java,
                    ),
                ).fromJson(roomJson)

        assertEquals(3, result?.size)
        val first = result?.first()
        assertEquals(1, first?.id)
        assertEquals(true, first?.isOccupied)
        assertEquals(53539L, first?.maxOccupancy)

        val createdAt = first?.createdAt
        assertEquals(2022, createdAt?.year)
        assertEquals(1, createdAt?.monthValue)
        assertEquals(24, createdAt?.dayOfMonth)
        assertEquals(20, createdAt?.hour)
        assertEquals(52, createdAt?.minute)
        assertEquals(50, createdAt?.second)
    }

    @Language("JSON")
    private val roomJson =
        """
        [
          {
            "createdAt":"2022-01-24T20:52:50.765Z",
            "isOccupied":true,
            "maxOccupancy":53539,
            "id":"1"
          },
          {
            "createdAt":"2022-01-25T14:37:26.128Z",
            "isOccupied":false,
            "maxOccupancy":34072,
            "id":"2"
          },
          {
            "createdAt":"2022-01-25T04:00:35.500Z",
            "isOccupied":false,
            "maxOccupancy":75480,
            "id":"3"
          }
        ]
        """.trimIndent()
}
