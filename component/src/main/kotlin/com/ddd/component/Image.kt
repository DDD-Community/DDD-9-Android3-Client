package com.ddd.component

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size


@Immutable
data class ImageSize(
    val width: Int,
    val height: Int,
) {
    constructor(size: Int) : this(size, size)

    constructor(width: Dp, height: Dp) : this(width.toPx(), height.toPx())

    constructor(size: Dp) : this(size, size)
}

@Composable
fun BDSImage(
    url: String?,
    modifier: Modifier = Modifier,
    size: ImageSize? = null,
    contentDescription: String? = null,
    tintColor: Color? = null,
    crossfade: Boolean = true,
    contentScale: ContentScale? = null,
    blendMode: BlendMode? = null,
) {
    BDSImage(
        any = url,
        modifier = modifier,
        size = size,
        contentDescription = contentDescription,
        tintColor = tintColor,
        crossfade = crossfade,
        contentScale = contentScale,
        blendMode = blendMode,
    )
}

@Composable
fun BDSImage(
    drawable: Drawable?,
    modifier: Modifier = Modifier,
    size: ImageSize? = null,
    contentDescription: String? = null,
    tintColor: Color? = null,
    crossfade: Boolean = true,
    contentScale: ContentScale? = null,
    blendMode: BlendMode? = null,
) {
    BDSImage(
        any = drawable,
        modifier = modifier,
        size = size,
        contentDescription = contentDescription,
        tintColor = tintColor,
        crossfade = crossfade,
        contentScale = contentScale,
        blendMode = blendMode,
    )
}

@Composable
fun BDSImage(
    @DrawableRes resId: Int?,
    modifier: Modifier = Modifier,
    size: ImageSize? = null,
    contentDescription: String? = null,
    tintColor: Color? = null,
    crossfade: Boolean = true,
    contentScale: ContentScale? = null,
    blendMode: BlendMode? = null,
) {
    BDSImage(
        any = resId,
        modifier = modifier,
        size = size,
        contentDescription = contentDescription,
        tintColor = tintColor,
        crossfade = crossfade,
        contentScale = contentScale,
        blendMode = blendMode,
    )
}

@Composable
private fun BDSImage(
    any: Any?,
    modifier: Modifier = Modifier,
    size: ImageSize? = null,
    contentDescription: String? = null,
    tintColor: Color? = null,
    crossfade: Boolean = true,
    contentScale: ContentScale? = null,
    blendMode: BlendMode? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(any)
            .crossfade(crossfade)
            .apply {
                if (size != null) size(Size(size.width, size.height))
            }
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale ?: ContentScale.Crop,
        modifier = modifier,
        colorFilter = tintColor?.let { ColorFilter.tint(it, blendMode ?: BlendMode.SrcIn) },
    )
}