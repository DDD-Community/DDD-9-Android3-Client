package com.ddd.component

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun BDSIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(imageVector = imageVector, contentDescription = null, tint = tint)
    }
}

@Composable
fun BDSIconButton(
    bitmap: ImageBitmap,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(bitmap = bitmap, contentDescription = null, tint = tint)
    }
}


@Composable
fun BDSIconButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(painter = painter, contentDescription = null, tint = tint)
    }
}

@Composable
fun BDSIconButton(
    @DrawableRes resId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(painter = painterResource(id = resId), contentDescription = null, tint = tint)
    }
}