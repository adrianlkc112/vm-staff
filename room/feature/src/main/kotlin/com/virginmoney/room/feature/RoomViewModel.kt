package com.virginmoney.room.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virginmoney.network.Response.Success
import com.virginmoney.room.data.Room
import com.virginmoney.room.data.RoomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RoomViewModel
    @Inject
    constructor(
        private val roomRepo: RoomRepo,
    ) : ViewModel() {
        private val roomsFlow = MutableStateFlow<List<Room>>(emptyList())
        private val errorFlow = MutableStateFlow(false)

        private val _uiState =
            combine(
                roomsFlow,
                errorFlow,
            ) { rooms, isError ->
                if (isError) {
                    ErrorRoomUiState
                } else if (rooms.isNotEmpty()) {
                    LoadedRoomUiState(rooms)
                } else {
                    LoadingRoomUiState
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                LoadingRoomUiState,
            )

        val uiState: StateFlow<RoomUiState>
            get() = _uiState

        init {
            getRooms()
        }

        internal fun getRooms(isRetry: Boolean = false) {
            viewModelScope.launch {
                if (isRetry) {
                    errorFlow.emit(false)
                    roomsFlow.emit(listOf())
                }

                when (
                    val response = roomRepo.getRooms()
                ) {
                    is Success -> {
                        roomsFlow.emit(response.data)
                    }
                    else -> {
                        errorFlow.emit(true)
                    }
                }
            }
        }
    }
