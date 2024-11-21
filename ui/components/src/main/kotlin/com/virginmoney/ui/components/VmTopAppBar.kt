package com.virginmoney.ui.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.virginmoney.ui.theming.VmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VmTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onPeopleClick: (() -> Unit)? = null,
    onRoomClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                    )
                }
            }
        },
        actions = {
            if (onPeopleClick != null) {
                IconButton(onClick = onPeopleClick) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = stringResource(R.string.staff_details),
                    )
                }
            }
            if (onRoomClick != null) {
                IconButton(onClick = onRoomClick) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = stringResource(R.string.check_room),
                    )
                }
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkVmTopAppBarPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightVmTopAppBarPreview",
)
@Composable
private fun VmTopAppBarPreview() {
    VmTheme {
        VmTopAppBar(
            title = "Test",
            onBackClick = {},
            onPeopleClick = {},
            onRoomClick = {},
        )
    }
}
