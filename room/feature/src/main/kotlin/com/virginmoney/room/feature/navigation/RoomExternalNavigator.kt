package com.virginmoney.room.feature.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency

interface RoomExternalNavigator {
    fun navigateToPeople()
}

@Composable
fun DependenciesContainerBuilder<*>.RoomDependencies(roomExternalNavigator: RoomExternalNavigator) {
    dependency(roomExternalNavigator)
}
