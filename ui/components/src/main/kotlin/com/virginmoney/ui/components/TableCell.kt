package com.virginmoney.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virginmoney.ui.theming.VmTheme

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    isTitle: Boolean = false,
) {
    Text(
        text = text,
        color =
            if (isTitle) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
        fontWeight = if (isTitle) FontWeight.Bold else FontWeight.Normal,
        modifier =
            Modifier
                .border(
                    width = 1.dp,
                    color =
                        if (isTitle) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                ).weight(weight)
                .background(
                    if (isTitle) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surface
                    },
                ).padding(8.dp),
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkTableCellDefaultPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightTableCellDefaultPreview",
)
@Composable
private fun TableCellDefaultPreview() {
    VmTheme {
        Surface {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            ) {
                TableCell(
                    text = "Test",
                    weight = 1f,
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkTableCellTitlePreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightTableCellTitlePreview",
)
@Composable
private fun TableCellTitlePreview() {
    VmTheme {
        Surface {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            ) {
                TableCell(
                    text = "Test",
                    weight = 1f,
                    isTitle = true,
                )
            }
        }
    }
}
