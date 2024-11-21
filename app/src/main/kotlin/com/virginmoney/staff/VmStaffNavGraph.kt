package com.virginmoney.staff

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.people.navgraphs.PeopleGraph

@NavHostGraph
annotation class VmStaffNavGraph {
    @ExternalNavGraph<PeopleGraph>
    companion object Includes
}
