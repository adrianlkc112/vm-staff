package com.virginmoney.people.feature

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.ramcosta.composedestinations.generated.people.destinations.PeopleDetailBottomSheetDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.virginmoney.people.data.People
import com.virginmoney.people.feature.navigation.PeopleExternalNavigator
import com.virginmoney.people.feature.navigation.PeopleNavGraph
import com.virginmoney.ui.components.TableCell
import com.virginmoney.ui.components.VmTopAppBar
import com.virginmoney.ui.theming.VmTheme

@Destination<PeopleNavGraph>(
    start = true,
)
@Composable
internal fun PeopleScreen(
    navigator: DestinationsNavigator,
    externalNavigator: PeopleExternalNavigator,
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
        onContentClick = { people ->
            navigator.navigate(
                PeopleDetailBottomSheetDestination(
                    people,
                ),
            )
        },
        onRoomClick = {
            externalNavigator.navigateToRoom()
        },
    )
}

@Composable
private fun PeopleScreen(
    uiState: PeopleUiState,
    onBackClick: () -> Unit,
    onContentClick: (People) -> Unit,
    onRoomClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            VmTopAppBar(
                title = stringResource(R.string.people_title),
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
                    onContentClick,
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
    onContentClick: (People) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(8.dp),
    ) {
        Text(stringResource(R.string.click_to_details))
        Spacer(Modifier.height(8.dp))
        TitlTable()
        Spacer(Modifier.height(8.dp))
        ContentTable(peoples, onContentClick)
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
            text = stringResource(R.string.first_name),
            weight = 0.5f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
        TableCell(
            text = stringResource(R.string.last_name),
            weight = 0.5f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
    }
}

@Composable
private fun ContentTable(
    peoples: List<People>,
    onContentClick: (People) -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(peoples) { people ->
            Row(
                modifier =
                    Modifier
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .clickable { onContentClick(people) },
            ) {
                TableCell(
                    text = people.firstName,
                    weight = 0.5f,
                    modifier = Modifier.fillMaxHeight(),
                )
                TableCell(
                    text = people.lastName,
                    weight = 0.5f,
                    modifier = Modifier.fillMaxHeight(),
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
            onContentClick = { _ -> },
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
            onContentClick = { _ -> },
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
            onContentClick = { _ -> },
            onRoomClick = {},
        )
    }
}
