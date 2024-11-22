package com.virginmoney.people.feature.bottomsheets

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.virginmoney.people.feature.R
import com.virginmoney.ui.theming.VmTheme

@Composable
fun Avatar(
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
) {
    val asyncImagePainter =
        rememberAsyncImagePainter(
            model =
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(imageUrl)
                    .size(coil.size.Size.ORIGINAL)
                    .placeholder(PlaceholderDrawable(LocalContext.current))
                    .crossfade(true)
                    .build(),
            contentScale = contentScale,
        )

    if (asyncImagePainter.state is State.Loading &&
        !LocalInspectionMode.current
        ) {
        LoadinggImage()
    }
    else if (asyncImagePainter.state is State.Error) {
        NoImagePlaceholder()
    } else {
        Image(
            painter = asyncImagePainter,
            contentDescription = stringResource(R.string.avatar),
            contentScale = contentScale,
            modifier = modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun LoadinggImage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NoImagePlaceholder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_image_placeholder),
            contentDescription = stringResource(R.string.avatar),
        )
    }
}

@Composable
private fun PlaceholderDrawable(context: Context): Drawable? {
    return if (LocalInspectionMode.current) {
        context.getDrawable(R.drawable.no_image_placeholder)
    } else {
        null
    }
}

@Preview
@Composable
fun AvatarPreview() {
    VmTheme {
        Box(Modifier.size(128.dp)) {
            Avatar(
                imageUrl = "https://abc.jpg",
            )
        }
    }
}
