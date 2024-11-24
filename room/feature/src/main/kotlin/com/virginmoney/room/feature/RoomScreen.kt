package com.virginmoney.room.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.room.data.Room
import com.virginmoney.ui.components.TableCell
import com.virginmoney.ui.components.VmTopAppBar
import com.virginmoney.ui.theming.VmTheme

@Destination<RoomNavGraph>(
    start = true,
)
@Composable
internal fun RoomScreen(
    navigator: DestinationsNavigator,
    viewModel: RoomViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    RoomScreen(
        uiState = uiState,
        onBackClick = {
            if (uiState !is LoadingRoomUiState) {
                navigator.popBackStack()
            }
        },
        onPeopleClick = {
            //navigator.navigate()
        },
    )
}

@Composable
private fun RoomScreen(
    uiState: RoomUiState,
    onBackClick: () -> Unit,
    onPeopleClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            VmTopAppBar(
                title = stringResource(R.string.room_title),
                onBackClick = onBackClick,
                onRoomClick = onPeopleClick,
            )
        },
    ) { innerPadding ->
        when (uiState) {
            is LoadingRoomUiState -> LoadingRoomContent()
            is LoadedRoomUiState ->
                LoadedRoomContent(
                    uiState.rooms,
                    modifier = Modifier.padding(innerPadding),
                )

            else -> ErrorRoomContent()
        }
    }
}

@Composable
private fun LoadingRoomContent() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier =
                Modifier
                    .size(64.dp)
                    .align(Alignment.Center),
        )
    }
}

@Composable
private fun LoadedRoomContent(
    rooms: List<Room>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(8.dp),
    ) {
        TitlTable()
        Spacer(Modifier.height(8.dp))
        ContentTable(rooms)
    }
}

@Composable
private fun TitlTable() {
    Row(
        modifier =
            Modifier
                .height(intrinsicSize = IntrinsicSize.Max),
    ) {
        TableCell(
            text = stringResource(R.string.id),
            weight = 0.15f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
        TableCell(
            text = stringResource(R.string.available),
            weight = 0.35f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
        TableCell(
            text = stringResource(R.string.max_occupancy),
            weight = 0.5f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
    }
}

@Composable
private fun ContentTable(rooms: List<Room>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(rooms) { room ->
            Row(
                modifier =
                    Modifier
                        .height(intrinsicSize = IntrinsicSize.Max),
            ) {
                TableCell(
                    text = room.id.toString(),
                    weight = 0.15f,
                    modifier = Modifier.fillMaxHeight(),
                )
                TableCell(
                    text =
                        if (room.isOccupied) {
                            stringResource(R.string.available_no)
                        } else {
                            stringResource(R.string.available_yes)
                        },
                    customTextColor =
                        if (room.isOccupied) {
                            MaterialTheme.colorScheme.error
                        } else {
                            Color.Green
                        },
                    weight = 0.35f,
                    modifier = Modifier.fillMaxHeight(),
                )
                TableCell(
                    text = room.maxOccupancy.toString(),
                    weight = 0.5f,
                    modifier = Modifier.fillMaxHeight(),
                )
            }
        }
    }
}

@Composable
private fun ErrorRoomContent() {
    // TODO: Add error screen & handling
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkLoadedRoomScreenPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightLoadedRoomScreenPreview",
)
@Preview
@Composable
private fun LoadedRoomScreenPreview() {
    VmTheme {
        RoomScreen(
            uiState = LoadedRoomUiState(Room.createMocks()),
            onBackClick = {},
            onPeopleClick = {},
        )
    }
}

@Preview
@Composable
private fun LoadingRoomScreenPreview() {
    VmTheme {
        RoomScreen(
            uiState = LoadingRoomUiState,
            onBackClick = {},
            onPeopleClick = {},
        )
    }
}

@Preview
@Composable
private fun ErrorRoomScreenPreview() {
    VmTheme {
        RoomScreen(
            uiState = ErrorRoomUiState,
            onBackClick = {},
            onPeopleClick = {},
        )
    }
}
