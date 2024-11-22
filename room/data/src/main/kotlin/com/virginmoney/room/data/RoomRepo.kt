package com.virginmoney.room.data

import com.virginmoney.network.Response

interface RoomRepo {
    suspend fun getRooms(): Response<List<Room>>
}
