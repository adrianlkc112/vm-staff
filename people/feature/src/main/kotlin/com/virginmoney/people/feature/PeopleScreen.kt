package com.virginmoney.people.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.people.data.People
import com.virginmoney.ui.components.TableCell
import com.virginmoney.ui.components.VmTopAppBar
import com.virginmoney.ui.theming.VmTheme

@Destination<PeopleNavGraph>(
    start = true,
)
@Composable
internal fun PeopleScreen(
    navigator: DestinationsNavigator,
    viewModel: PeopleViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PeopleScreen(
        uiState = uiState,
        onBackClick = {
            if (uiState !is LoadingPeopleUiState) {
                navigator.popBackStack()
            }
        },
        onRoomClick = {
        },
    )
}

@Composable
private fun PeopleScreen(
    uiState: PeopleUiState,
    onBackClick: () -> Unit,
    onRoomClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            VmTopAppBar(
                title = stringResource(R.string.title),
                onBackClick = onBackClick,
                onRoomClick = onRoomClick,
            )
        },
    ) { innerPadding ->
        when (uiState) {
            is LoadingPeopleUiState -> LoadingPeopleContent()
            is LoadedPeopleUiState ->
                LoadedPeopleContent(
                    uiState.peoples,
                    modifier = Modifier.padding(innerPadding),
                )
            else -> ErrorPeopleContent()
        }
    }
}

@Composable
private fun LoadingPeopleContent() {
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
private fun LoadedPeopleContent(
    peoples: List<People>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        TitlTable()
        Spacer(Modifier.height(8.dp))
        ContentTable(peoples)
    }
}

@Composable
private fun TitlTable() {
    Row {
        TableCell(
            text = stringResource(R.string.first_name),
            weight = 0.5f,
            isTitle = true,
        )
        TableCell(
            text = stringResource(R.string.last_name),
            weight = 0.5f,
            isTitle = true,
        )
    }
}

@Composable
private fun ContentTable(peoples: List<People>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(peoples) { people ->
            Row {
                TableCell(
                    text = people.firstName,
                    weight = 0.5f,
                )
                TableCell(
                    text = people.lastName,
                    weight = 0.5f,
                )
            }
        }
    }
}

@Composable
private fun ErrorPeopleContent() {
    // TODO: Add error screen & handling
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkLoadedPeopleScreenPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightLoadedPeopleScreenPreview",
)
@Preview
@Composable
private fun LoadedPeopleScreenPreview() {
    VmTheme {
        PeopleScreen(
            uiState = LoadedPeopleUiState(People.createMocks()),
            onBackClick = {},
            onRoomClick = {},
        )
    }
}

@Preview
@Composable
private fun LoadingPeopleScreenPreview() {
    VmTheme {
        PeopleScreen(
            uiState = LoadingPeopleUiState,
            onBackClick = {},
            onRoomClick = {},
        )
    }
}

@Preview
@Composable
private fun ErrorPeopleScreenPreview() {
    VmTheme {
        PeopleScreen(
            uiState = ErrorPeopleUiState,
            onBackClick = {},
            onRoomClick = {},
        )
    }
}
