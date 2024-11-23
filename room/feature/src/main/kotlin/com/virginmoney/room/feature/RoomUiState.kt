package com.virginmoney.room.feature

import com.virginmoney.room.data.Room

internal sealed class RoomUiState

internal data object ErrorRoomUiState : RoomUiState()

internal data object LoadingRoomUiState : RoomUiState()

internal data class LoadedRoomUiState(
    val rooms: List<Room>,
) : RoomUiState()
