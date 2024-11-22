package com.virginmoney.people.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
@JsonClass(generateAdapter = true)
class People(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String,
    val createdAt: LocalDateTime,
    @Json(name = "jobtitle")
    val jobTitle: String,
    val favouriteColor: String,
) : Parcelable {
    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        fun createMocks(): List<People> =
            listOf(
                People(
                    id = 1,
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@example.com",
                    avatar = "https://example.com/avatar1.jpg",
                    createdAt = LocalDateTime.parse("2022-01-24T17:02:23.729Z", dateTimeFormatter),
                    jobTitle = "Software Engineer",
                    favouriteColor = "Blue",
                ),
                People(
                    id = 2,
                    firstName = "William",
                    lastName = "Harrison",
                    email = "william.harrison@example.com",
                    avatar = "https://example.com/avatar2.jpg",
                    createdAt = LocalDateTime.parse("2022-01-25T17:02:23.729Z", dateTimeFormatter),
                    jobTitle = "Future Functionality Strategist",
                    favouriteColor = "Pink",
                ),
                People(
                    id = 3,
                    firstName = "June",
                    lastName = "Turner",
                    email = "jane.turner@example.com",
                    avatar = "https://example.com/avatar3.jpg",
                    createdAt = LocalDateTime.parse("2022-01-26T17:02:23.729Z", dateTimeFormatter),
                    jobTitle = "Principal Accounts Developer",
                    favouriteColor = "Green",
                ),
                People( // Duplicate data
                    id = 4,
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@example.com",
                    avatar = "https://example.com/avatar1.jpg",
                    createdAt = LocalDateTime.parse("2022-01-24T17:02:23.729Z", dateTimeFormatter),
                    jobTitle = "Software Engineer",
                    favouriteColor = "Blue",
                ),
            )
    }
}
