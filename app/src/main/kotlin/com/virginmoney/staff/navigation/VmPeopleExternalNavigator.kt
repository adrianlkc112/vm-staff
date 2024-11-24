package com.virginmoney.staff.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.room.destinations.RoomScreenDestination
import com.virginmoney.people.feature.navigation.PeopleExternalNavigator

class VmPeopleExternalNavigator(
    private val navController: NavHostController,
) : PeopleExternalNavigator {
    override fun navigateToRoom() {
        val navOptions =
            NavOptions
                .Builder()
                .setPopUpTo(HomeScreenDestination.route, false)
                .build()
        navController.navigate(RoomScreenDestination.route, navOptions)
    }
}
