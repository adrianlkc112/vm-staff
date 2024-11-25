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
        private var cachedPeoples: List<People>? = null

        override suspend fun getPeopleDetails(): Response<List<People>> {
            return try {
                val cache = cachedPeoples
                if (cache != null) return Response.Success(cache)

                val response = removeDuplicateAndSort(peopleService.getPeopleDetails())
                cachedPeoples = response
                Response.Success(response)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }

        private fun removeDuplicateAndSort(peoples: List<People>): List<People> =
            peoples
                .distinctBy { it.firstName + it.lastName + it.email }
                .sortedWith(
                    compareBy(People::firstName, People::lastName),
                )
    }
