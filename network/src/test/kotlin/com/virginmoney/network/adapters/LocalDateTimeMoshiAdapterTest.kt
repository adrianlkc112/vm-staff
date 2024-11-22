package com.virginmoney.network.adapters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.virginmoney.network.NetworkModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
class TestClassWithLocalDateTimeField(
    @Json(name = "localDateTime") val localDateTime: LocalDateTime,
)

class LocalDateTimeMoshiAdapterTest {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    @Test
    fun `LocalDateTimeMoshiAdapter - should convert strings to LocalDateTime objects`() {
        val moshi = NetworkModule.provideMoshi()

        val result =
            moshi
                .adapter(TestClassWithLocalDateTimeField::class.java)
                .fromJson("{ \"localDateTime\": \"2023-12-31T23:59:59.000Z\" }")

        assertEquals(
            LocalDateTime.parse("2023-12-31T23:59:59.000Z", dateTimeFormatter),
            result?.localDateTime,
        )
    }

    @Test
    fun `LocalDateTimeMoshiAdapter - should convert LocateDateTime objects into strings`() {
        val moshi = NetworkModule.provideMoshi()

        val result =
            moshi
                .adapter(TestClassWithLocalDateTimeField::class.java)
                .toJson(
                    TestClassWithLocalDateTimeField(
                        localDateTime = LocalDateTime.parse("2023-12-30T22:48:48.000Z", dateTimeFormatter),
                    ),
                )

        assertEquals("{\"localDateTime\":\"2023-12-30T22:48:48.000Z\"}", result)
    }

    @Test
    fun `LocalDateTimeMoshiAdapter - when datetime format is not recognised - should throw an exception`() {
        val moshi = NetworkModule.provideMoshi()

        val result =
            try {
                moshi
                    .adapter(TestClassWithLocalDateTimeField::class.java)
                    .fromJson("{ \"localDateTime\": \"2023-12-29\" }")
            } catch (e: Exception) {
                e
            }

        assertTrue(result is Exception)
        assertEquals(
            "java.time.format.DateTimeParseException: Text '2023-12-29' could not be parsed at index 10 at \$.localDateTime",
            (result as Exception).message,
        )
    }
}
