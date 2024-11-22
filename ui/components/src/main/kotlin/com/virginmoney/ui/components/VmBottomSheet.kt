package com.virginmoney.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virginmoney.ui.theming.VmTheme

@Composable
fun VmBottomSheet(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface {
        BottomSheet(
            header = {
                BottomSheetHeader(
                    title = title,
                )
            },
            content = {
                content()
            },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BottomSheet(
    header: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier =
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                ),
    ) {
        header()
        content()
        Spacer(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.navigationBarsIgnoringVisibility,
            ),
        )
    }
}

@Composable
private fun BottomSheetHeader(title: String) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier =
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkVmBottomSheetPreview",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightVmBottomSheetPreview",
)
@Composable
private fun VmBottomSheetPreview() {
    VmTheme {
        VmBottomSheet("Title") {
            Column {
                Text(
                    text = "Test BottomSheet Start",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(200.dp))
                Text(
                    text = "Test BottomSheet End",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
