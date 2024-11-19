package com.virginmoney.people.data

import com.virginmoney.network.Response

interface PeopleRepo {
    suspend fun getPeopleDetails(): Response<List<People>>
}
