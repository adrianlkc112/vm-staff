package com.virginmoney.people.feature.bottomsheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.bottomsheet.spec.DestinationStyleBottomSheet
import com.virginmoney.people.data.People
import com.virginmoney.people.feature.R
import com.virginmoney.people.feature.navigation.PeopleNavGraph
import com.virginmoney.ui.components.TableCell
import com.virginmoney.ui.components.VmBottomSheet
import com.virginmoney.ui.theming.VmTheme
import java.time.format.DateTimeFormatter

@Destination<PeopleNavGraph>(style = DestinationStyleBottomSheet::class)
@Composable
internal fun PeopleDetailBottomSheet(people: People) {
    VmBottomSheet(
        title = "${people.firstName} ${people.lastName}",
        content = {
            PeopleDetailContent(people)
        },
    )
}

@Composable
private fun PeopleDetailContent(people: People) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.size(128.dp)) {
            Avatar(people.avatar)
        }
        Spacer(Modifier.height(8.dp))
        PeopleDetailTable(people)
    }
}

@Composable
private fun PeopleDetailTable(people: People) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        TableRow(stringResource(R.string.id), people.id.toString())
        TableRow(stringResource(R.string.first_name), people.firstName)
        TableRow(stringResource(R.string.last_name), people.lastName)
        TableRow(stringResource(R.string.job_title), people.jobTitle)
        TableRow(stringResource(R.string.email), people.email)
        TableRow(
            stringResource(R.string.created_at),
            people.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        )
        TableRow(stringResource(R.string.favourite_color), people.favouriteColor)
    }
}

@Composable
private fun TableRow(
    name: String,
    value: String,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max),
    ) {
        TableCell(
            text = name,
            weight = 0.5f,
            isTitle = true,
            modifier = Modifier.fillMaxHeight(),
        )
        TableCell(
            text = value,
            weight = 0.5f,
            isTitle = false,
            modifier = Modifier.fillMaxHeight(),
        )
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun PeopleDetailBottomSheetPreview() {
    VmTheme {
        PeopleDetailBottomSheet(People.createMocks().first())
    }
}
