package com.ddd.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BDSCheckbox(
    checkedImage: ImageVector,
    uncheckedImage: ImageVector,
    disabledImage: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    enabled: Boolean = true
) {
    Icon(
        imageVector = when {
            !enabled && disabledImage != null -> disabledImage
            checked -> checkedImage
            else -> uncheckedImage
        },
        contentDescription = "",
        modifier = Modifier.clickable(enabled = enabled, onClick = onClick),
        tint = Color.Unspecified
    )
}

@Composable
fun BDSCheckbox(
    checkedImage: ImageBitmap,
    uncheckedImage: ImageBitmap,
    disabledImage: ImageBitmap? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    enabled: Boolean = true
) {
    Icon(
        bitmap = when {
            !enabled && disabledImage != null -> disabledImage
            checked -> checkedImage
            else -> uncheckedImage
        },
        contentDescription = "",
        modifier = Modifier.clickable(enabled = enabled, onClick = onClick),
        tint = Color.Unspecified
    )
}

@Composable
fun BDSCheckbox(
    @DrawableRes checkedImage: Int,
    @DrawableRes uncheckedImage: Int,
    @DrawableRes disabledImage: Int? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean = true,
    enabled: Boolean = true
) {
    Icon(
        painter = when {
            !enabled && disabledImage != null -> painterResource(id = disabledImage)
            checked -> painterResource(id = checkedImage)
            else -> painterResource(id = uncheckedImage)
        },
        contentDescription = "",
        modifier = Modifier
            .clickable(enabled = enabled, onClick = onClick)
            .then(modifier),
        tint = Color.Unspecified
    )
}

@Preview
@Composable
fun PreviewBDSCheckbox() {
    BDSCheckbox(
        checkedImage = R.drawable.ic_check,
        uncheckedImage = R.drawable.ic_uncheck,
        onClick = { /*TODO*/ },
        enabled = false
    )
}