package com.virginmoney.people.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virginmoney.network.Response.Success
import com.virginmoney.people.data.People
import com.virginmoney.people.data.PeopleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PeopleViewModel
    @Inject
    constructor(
        private val peopleRepo: PeopleRepo,
    ) : ViewModel() {
        private val peopleDetailsFlow = MutableStateFlow<List<People>>(emptyList())
        private val errorFlow = MutableStateFlow(false)

        private val _uiState =
            combine(
                peopleDetailsFlow,
                errorFlow,
            ) { peoples, isError ->
                if (isError) {
                    ErrorPeopleUiState
                } else if (peoples.isNotEmpty()) {
                    LoadedPeopleUiState(peoples)
                } else {
                    LoadingPeopleUiState
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                LoadingPeopleUiState,
            )

        val uiState: StateFlow<PeopleUiState>
            get() = _uiState

        init {
            getPeopleDetails()
        }

        internal fun getPeopleDetails(isRetry: Boolean = false) {
            viewModelScope.launch {
                if (isRetry) {
                    errorFlow.emit(false)
                    peopleDetailsFlow.emit(listOf())
                }

                when (
                    val response = peopleRepo.getPeopleDetails()
                ) {
                    is Success -> {
                        peopleDetailsFlow.emit(response.data)
                    }
                    else -> {
                        errorFlow.emit(true)
                    }
                }
            }
        }
    }
