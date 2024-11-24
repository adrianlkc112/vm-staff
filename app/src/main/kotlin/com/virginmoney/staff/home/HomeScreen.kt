package com.virginmoney.staff.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.generated.people.destinations.PeopleScreenDestination
import com.ramcosta.composedestinations.generated.room.destinations.RoomScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.staff.R
import com.virginmoney.staff.navigation.VmStaffNavGraph
import com.virginmoney.ui.components.VmTopAppBar
import com.virginmoney.ui.theming.VmTheme

@Destination<VmStaffNavGraph>(start = true)
@Composable
internal fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        onPeopleClick = {
            navigator.navigate(PeopleScreenDestination)
        },
        onRoomClick = {
            navigator.navigate(RoomScreenDestination)
        },
    )
}

@Composable
private fun HomeScreen(
    onPeopleClick: () -> Unit,
    onRoomClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            VmTopAppBar(
                title = stringResource(R.string.app_name),
                onPeopleClick = onPeopleClick,
                onRoomClick = onRoomClick,
            )
        },
    ) { innerPadding ->
        Greeting(
            modifer = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun Greeting(modifer: Modifier = Modifier) {
    Box(
        modifier = modifer.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("Welcome!")
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkGreetingPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightGreetingPreview",
)
@Composable
private fun GreetingPreview() {
    VmTheme {
        HomeScreen(
            onPeopleClick = {},
            onRoomClick = {},
        )
    }
}
