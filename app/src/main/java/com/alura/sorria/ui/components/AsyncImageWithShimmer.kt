package com.alura.sorria.ui.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.alura.sorria.R

@Composable
fun AsyncImageWithShimmer(
    data: Any,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeHolder: Int = R.drawable.shimmer_animation,
    error: Int = R.drawable.ic_error,
    contentScale: ContentScale = ContentScale.Crop
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data)
                .placeholder(placeHolder)
                .error(error)
                .build(),
            imageLoader = imageLoader
        ),
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}