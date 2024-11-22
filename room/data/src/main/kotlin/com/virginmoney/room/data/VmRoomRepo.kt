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
        override suspend fun getRooms(): Response<List<Room>> =
            try {
                val response = roomService.getRooms()
                Response.Success(
                    sort(response),
                )
            } catch (e: Exception) {
                Response.Failure(e)
            }

        private fun sort(rooms: List<Room>): List<Room> =
            rooms.sortedBy { room ->
                room.id
            }
    }
