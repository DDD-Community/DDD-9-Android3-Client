package com.ddd.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BodyView(
    modifier: Modifier = Modifier,
    left: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 13.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        left?.invoke()
        right?.invoke()
    }
}