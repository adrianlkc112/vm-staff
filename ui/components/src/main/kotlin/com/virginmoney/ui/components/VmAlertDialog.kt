package com.virginmoney.ui.components

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.virginmoney.ui.theming.VmTheme

@Composable
fun VmAlertDialog(
    title: String = stringResource(R.string.try_again_title),
    content: String = stringResource(R.string.try_again_body),
    confirmBtnText: String = stringResource(R.string.try_again_button),
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(title)
        },
        text = {
            Text(content)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                },
            ) {
                Text(confirmBtnText)
            }
        },
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkVmAlertDialogPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightVmAlertDialogPreview",
)
@Composable
private fun VmAlertDialogPreview() {
    VmTheme {
        VmAlertDialog(
            onConfirmation = {},
            onDismissRequest = {},
        )
    }
}
