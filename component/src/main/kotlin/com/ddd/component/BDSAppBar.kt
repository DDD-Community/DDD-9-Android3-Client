package com.ddd.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BDSAppBar(
    modifier: Modifier = Modifier,
    left: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
    center: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterStart),
            contentAlignment = Alignment.CenterStart,
        ) {
            left?.invoke()
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            center?.invoke()
        }

        Box(
            modifier = Modifier.align(Alignment.CenterEnd),
            contentAlignment = Alignment.CenterEnd
        ) {
            right?.invoke()
        }
    }
}

@Composable
fun BDSAppBar(
    modifier: Modifier = Modifier,
    left: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
    title: String? = null,
) {
    BDSAppBar(
        modifier = modifier,
        left = left,
        right = right,
        center = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                BDSText(
                    text = title ?: "",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
            }
        }
    )
}

@Composable
fun AppBarUpButton(onUpButtonClick: () -> Unit) {
    Column {
        BDSImage(
            modifier = Modifier
                .size(24.dp)
                .clickableWithRoundedRipple {
                    onUpButtonClick()
                },
            resId = R.drawable.ic_back,
            tintColor = Color.Black,
            contentScale = ContentScale.Fit
        )
    }
}