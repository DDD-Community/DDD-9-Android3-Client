package com.ddd.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.Gray800
import com.ddd.component.theme.BDSColor.Primary400
import com.ddd.component.theme.BDSColor.White

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
                text = text,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = SemiBold
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
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = SemiBold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun BDSIconSnackbar(
    text: String,
    icon: Painter,
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
            Row() {
                Icon(
                    painter = icon,
                    contentDescription = "",
                    modifier = Modifier.padding(1.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BDSText(
                    modifier = modifier.fillMaxWidth(),
                    text = text,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = SemiBold
                )
            }
        }
    }
}