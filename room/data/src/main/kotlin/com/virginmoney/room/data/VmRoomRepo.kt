package com.virginmoney.room.data

import com.virginmoney.network.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class VmRoomRepo
    @Inject
    constructor(
        private val roomService: RoomService,
    ) : RoomRepo {
        private var cachedRooms: List<Room>? = null

        override suspend fun getRooms(): Response<List<Room>> {
            return try {
                val cache = cachedRooms
                if (cache != null) return Response.Success(cache)

                val response = sort(roomService.getRooms())
                cachedRooms = response
                Response.Success(response)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }

        private fun sort(rooms: List<Room>): List<Room> =
            rooms.sortedBy { room ->
                room.id
            }
    }
