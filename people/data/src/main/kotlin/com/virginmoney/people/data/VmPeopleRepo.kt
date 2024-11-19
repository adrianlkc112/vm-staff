package com.virginmoney.people.data

import com.virginmoney.network.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class VmPeopleRepo
    @Inject
    constructor(
        private val peopleService: PeopleService,
    ) : PeopleRepo {
        override suspend fun getPeopleDetails(): Response<List<People>> =
            try {
                val response = peopleService.getPeopleDetails()
                Response.Success(response)
            } catch (e: Exception) {
                Response.Failure(e)
            }
    }
