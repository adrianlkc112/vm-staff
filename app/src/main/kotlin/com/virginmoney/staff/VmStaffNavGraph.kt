package com.virginmoney.staff

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.people.navgraphs.PeopleGraph
import com.ramcosta.composedestinations.generated.room.navgraphs.RoomGraph

@NavHostGraph
annotation class VmStaffNavGraph {
    @ExternalNavGraph<PeopleGraph>
    @ExternalNavGraph<RoomGraph>
    companion object Includes
}
