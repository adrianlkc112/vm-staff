package com.virginmoney.people.data

import retrofit2.http.GET

interface PeopleService {
    @GET("people")
    suspend fun getPeopleDetails(): List<People>
}
