package com.virginmoney.staff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.navigation.BottomSheetNavigator
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.people.navgraphs.PeopleGraph
import com.ramcosta.composedestinations.generated.room.navgraphs.RoomGraph
import com.ramcosta.composedestinations.navigation.navGraph
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import com.virginmoney.people.feature.navigation.PeopleDependencies
import com.virginmoney.room.feature.navigation.RoomDependencies
import com.virginmoney.staff.navigation.VmNavHostAnimatedStyle
import com.virginmoney.staff.navigation.VmPeopleExternalNavigator
import com.virginmoney.staff.navigation.VmRoomExternalNavigator
import com.virginmoney.ui.theming.VmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VmTheme {
                val sheetState =
                    rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.Hidden,
                        skipHalfExpanded = true,
                    )
                val navController = rememberNavController()
                val bottomSheetNavigator = remember { BottomSheetNavigator(sheetState) }
                navController.navigatorProvider += bottomSheetNavigator
                val destinationsNavigator = navController.rememberDestinationsNavigator()
                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomSheetNavigator,
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.vmStaff,
                        defaultTransitions = VmNavHostAnimatedStyle(),
                        dependenciesContainerBuilder = {
                            navGraph(PeopleGraph) {
                                val externalNavigator =
                                    remember(navBackStackEntry) {
                                        VmPeopleExternalNavigator(destinationsNavigator)
                                    }
                                PeopleDependencies(externalNavigator)
                            }
                            navGraph(RoomGraph) {
                                val externalNavigator =
                                    remember(navBackStackEntry) {
                                        VmRoomExternalNavigator(destinationsNavigator)
                                    }
                                RoomDependencies(externalNavigator)
                            }
                        },
                    )
                }
            }
        }
    }
}
