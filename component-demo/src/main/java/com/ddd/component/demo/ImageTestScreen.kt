package com.ddd.component.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.component.BDSImage
import com.ddd.component.ImageSize
import com.ddd.component.theme.BDSColor.Gray100

@Composable
@Preview(showBackground = true)
fun ImageTestScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BDSImage(
            resId = com.google.android.material.R.drawable.ic_clear_black_24,
            tintColor = White,
            modifier = Modifier
                .background(color = Gray100, shape = CircleShape)
                .size(24.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        BDSImage(
            url = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            size = ImageSize(200.dp, 120.dp),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = Gray100),
        )
    }
}