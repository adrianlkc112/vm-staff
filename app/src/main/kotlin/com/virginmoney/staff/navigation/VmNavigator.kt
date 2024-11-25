package com.virginmoney.staff.navigation

import androidx.navigation.NavOptions
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.people.destinations.PeopleScreenDestination
import com.ramcosta.composedestinations.generated.room.destinations.RoomScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class VmNavigator {
    companion object {
        fun navigateToRoom(navigator: DestinationsNavigator) {
            val navOptions = createNavOptions()
            navigator.navigate(RoomScreenDestination, navOptions)
        }

        fun navigateToPeople(navigator: DestinationsNavigator) {
            val navOptions = createNavOptions()
            navigator.navigate(PeopleScreenDestination, navOptions)
        }

        private fun createNavOptions(): NavOptions =
            NavOptions
                .Builder()
                .setPopUpTo(HomeScreenDestination.route, false)
                .build()
    }
}
