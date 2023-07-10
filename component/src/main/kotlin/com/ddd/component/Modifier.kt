package com.ddd.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ddd.component.theme.BDSColor.Gray400


@Composable
fun Modifier.clickableWithRoundedRipple(
    enabled: Boolean = true,
    radius: Dp = 24.dp,
    onClick: () -> Unit,
): Modifier = clickable(
    enabled = enabled,
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple(
        bounded = false,
        radius = radius,
        color = Gray400
    ),
    onClick = onClick
)

@Composable
fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = clickable(
    enabled = enabled,
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)