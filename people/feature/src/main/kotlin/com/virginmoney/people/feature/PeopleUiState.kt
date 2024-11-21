package com.virginmoney.people.feature

import com.virginmoney.people.data.People

internal sealed class PeopleUiState

internal data object ErrorPeopleUiState : PeopleUiState()

internal data object LoadingPeopleUiState : PeopleUiState()

internal data class LoadedPeopleUiState(
    val peoples: List<People>,
) : PeopleUiState()
