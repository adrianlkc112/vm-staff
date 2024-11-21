package com.virginmoney.people.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class People(
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String,
    val jobTitle: String?,
    val favouriteColor: String,
) {
    companion object {
        fun createMocks(): List<People> =
            listOf(
                People(
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@example.com",
                    avatar = "https://example.com/avatar1.jpg",
                    jobTitle = "Software Engineer",
                    favouriteColor = "Blue",
                ),
                People(
                    firstName = "William",
                    lastName = "Harrison",
                    email = "william.harrison@example.com",
                    avatar = "https://example.com/avatar2.jpg",
                    jobTitle = "Future Functionality Strategist",
                    favouriteColor = "Pink",
                ),
                People(
                    firstName = "Jane",
                    lastName = "Turner",
                    email = "jane.turner@example.com",
                    avatar = "https://example.com/avatar3.jpg",
                    jobTitle = "Principal Accounts Developer",
                    favouriteColor = "Green",
                ),
            )
    }
}
