package com.virginmoney.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virginmoney.ui.theming.VmTheme

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    isBold: Boolean = false,
    backgroundColor: Color = Color.Transparent,
) {
    Text(
        text = text,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .background(backgroundColor)
            .padding(8.dp),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun TableCellDefaultPreview() {
    VmTheme {
        Row(
            modifier = Modifier
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

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun TableCellBoldTextWithBgColourPreview() {
    VmTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            TableCell(
                text = "Test",
                weight = 1f,
                backgroundColor = Color.Gray,
                isBold = true,
            )
        }
    }
}
