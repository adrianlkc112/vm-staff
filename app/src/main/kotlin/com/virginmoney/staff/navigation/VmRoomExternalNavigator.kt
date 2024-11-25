package com.virginmoney.staff.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.room.feature.navigation.RoomExternalNavigator

class VmRoomExternalNavigator(
    private val navigator: DestinationsNavigator,
) : RoomExternalNavigator {
    override fun navigateToPeople() {
        VmNavigator.navigateToPeople(navigator)
    }
}
