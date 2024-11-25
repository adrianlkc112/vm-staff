package com.virginmoney.staff.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.people.destinations.PeopleScreenDestination
import com.virginmoney.room.feature.navigation.RoomExternalNavigator

class VmRoomExternalNavigator(
    private val navController: NavHostController,
) : RoomExternalNavigator {
    override fun navigateToPeople() {
        val navOptions =
            NavOptions
                .Builder()
                .setPopUpTo(HomeScreenDestination.route, false)
                .build()
        navController.navigate(PeopleScreenDestination.route, navOptions)
    }
}
