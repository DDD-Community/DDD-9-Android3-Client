package com.ddd.component

import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.component.theme.BDSColor.Primary400
import com.ddd.component.theme.BDSColor.SlateGray400
import com.ddd.component.theme.BDSColor.White

@Composable
fun BDSSwitch(
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            checkedBorderColor = Color.Transparent,
            checkedThumbColor = White,
            checkedTrackColor = Primary400,
            uncheckedBorderColor = Color.Transparent,
            uncheckedThumbColor = White,
            uncheckedTrackColor = SlateGray400,
            disabledCheckedThumbColor = White.copy(alpha = ContentAlpha.disabled),
            disabledCheckedTrackColor = Primary400.copy(alpha = ContentAlpha.disabled),
            disabledUncheckedThumbColor = White.copy(alpha = ContentAlpha.disabled),
            disabledUncheckedTrackColor = SlateGray400.copy(alpha = ContentAlpha.disabled),
        )
    )
}

@Preview
@Composable
fun PreviewBDSSwitch() {
    BDSSwitch(checked = true, onCheckedChange = null)
}