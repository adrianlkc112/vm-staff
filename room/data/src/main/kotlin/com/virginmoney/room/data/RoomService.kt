package com.virginmoney.room.data

import retrofit2.http.GET

interface RoomService {
    @GET("rooms")
    suspend fun getRooms(): List<Room>
}
