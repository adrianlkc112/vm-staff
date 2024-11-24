package com.virginmoney.people.feature.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency

interface PeopleExternalNavigator {
    fun navigateToRoom()
}

@Composable
fun DependenciesContainerBuilder<*>.PeopleDependencies(peopleExternalNavigator: PeopleExternalNavigator) {
    dependency(peopleExternalNavigator)
}
