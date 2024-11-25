package com.virginmoney.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.virginmoney.ui.theming.VmTheme

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    isTitle: Boolean = false,
    customTextColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color =
            customTextColor
                ?: if (isTitle) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
        fontWeight = if (isTitle) FontWeight.Bold else FontWeight.Normal,
        modifier =
            modifier
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

@PreviewLightDark
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

@PreviewLightDark
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

@Preview
@Composable
private fun TableCellWithCustomTextColorPreview() {
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
                    customTextColor = Color.Green,
                )
            }
        }
    }
}
