package com.virginmoney.staff.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.staff.R
import com.virginmoney.staff.navigation.VmNavigator
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
            VmNavigator.navigateToPeople(navigator)
        },
        onRoomClick = {
            VmNavigator.navigateToRoom(navigator)
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
            onPeopleClick = onPeopleClick,
            onRoomClick = onRoomClick,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun Greeting(
    onPeopleClick: () -> Unit,
    onRoomClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement =
            Arrangement.spacedBy(
                36.dp,
                Alignment.CenterVertically,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Welcome!",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
        GreetingOutlinedButton(
            text = stringResource(R.string.staff_details),
            iconImageVector = Icons.Filled.Person,
            onButtonClick = onPeopleClick,
        )
        GreetingOutlinedButton(
            text = stringResource(R.string.check_rooms),
            iconImageVector = Icons.Filled.DateRange,
            onButtonClick = onRoomClick,
        )
    }
}

@Composable
private fun GreetingOutlinedButton(
    text: String,
    iconImageVector: ImageVector,
    onButtonClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onButtonClick,
    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = iconImageVector,
            contentDescription = null,
        )
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun GreetingPreview() {
    VmTheme {
        HomeScreen(
            onPeopleClick = {},
            onRoomClick = {},
        )
    }
}
