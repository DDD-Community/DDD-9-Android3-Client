package com.ddd.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ddd.component.theme.Gray800
import com.ddd.component.theme.Primary400
import com.ddd.component.theme.White

@Composable
@ExperimentalMaterial3Api
fun BDSSnackbar(
    text: String,
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
    dismissAction: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = Primary400,
    contentColor: Color = White,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier)
    ) {
        Snackbar(
            modifier = modifier,
            action = action,
            dismissAction = dismissAction,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            actionContentColor = actionContentColor,
            dismissActionContentColor = dismissActionContentColor
        ) {
            BDSText(
                text = text
            )
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun BDSSingleTextSnackbar(
    text: String,
    modifier: Modifier = Modifier,
    dismissAction: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = Gray800,
    contentColor: Color = White,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,
) {
    Box(modifier = modifier.padding(8.dp)) {
        Snackbar(
            modifier = modifier,
            dismissAction = dismissAction,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            actionContentColor = actionContentColor,
            dismissActionContentColor = dismissActionContentColor,
        ) {
            BDSText(
                text = text,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}