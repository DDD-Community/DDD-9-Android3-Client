package com.ddd.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BDSAppBar(
    modifier: Modifier = Modifier,
    left: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
    title: String? = null,
) {
    TopAppBar(
        modifier = Modifier
            .height(46.dp)
            .then(modifier),
        title = {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.align(Alignment.CenterStart),
                    contentAlignment = Alignment.CenterStart
                ) {
                    left?.invoke()
                }

                BDSText(
                    text = title ?: "",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )

                Box(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    right?.invoke()
                }
            }
        },
    )
}

@Composable
fun AppBarUpButton(onUpButtonClick: () -> Unit) {
    Column {
        BDSImage(
            modifier = Modifier
                .size(24.dp)
                .padding(2.dp)
                .clickable {
                    onUpButtonClick()
                },
            resId = R.drawable.ic_back,
            tintColor = Color.Black,
            contentScale = ContentScale.Fit
        )
    }
}