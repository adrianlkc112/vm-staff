package com.virginmoney.room.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
@JsonClass(generateAdapter = true)
class Room(
    val id: Int,
    val isOccupied: Boolean,
    val createdAt: LocalDateTime,
    val maxOccupancy: Long,
) : Parcelable {
    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        fun createMocks(): List<Room> =
            listOf(
                Room(
                    id = 1,
                    isOccupied = false,
                    createdAt = LocalDateTime.parse("2022-01-24T17:02:23.729Z", dateTimeFormatter),
                    maxOccupancy = 84245,
                ),
                Room(
                    id = 3,
                    isOccupied = true,
                    createdAt = LocalDateTime.parse("2022-01-25T10:22:23.729Z", dateTimeFormatter),
                    maxOccupancy = 54245,
                ),
                Room(
                    id = 2,
                    isOccupied = false,
                    createdAt = LocalDateTime.parse("2022-01-26T12:44:23.729Z", dateTimeFormatter),
                    maxOccupancy = 74245,
                ),
            )
    }
}
