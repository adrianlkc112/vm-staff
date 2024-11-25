package com.virginmoney.staff.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.people.feature.navigation.PeopleExternalNavigator

class VmPeopleExternalNavigator(
    private val navigator: DestinationsNavigator,
) : PeopleExternalNavigator {
    override fun navigateToRoom() {
        VmNavigator.navigateToRoom(navigator)
    }
}
