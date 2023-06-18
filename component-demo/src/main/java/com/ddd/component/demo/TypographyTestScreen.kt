package com.ddd.component.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.component.BDSDivider
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSFontFamily

@Composable
@Preview
fun TypographyTestScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BDSText(
            text = "바이올낫 titleLarge 영문 폰트",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = BDSFontFamily.English
        )
        BDSText(
            text = "바이올낫 titleLarge",
            style = MaterialTheme.typography.titleLarge
        )
        BDSText(
            text = "바이올낫 titleMedium",
            style = MaterialTheme.typography.titleMedium
        )
        BDSText(
            text = "바이올낫 titleSmall",
            style = MaterialTheme.typography.titleSmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        BDSText(
            text = "바이올낫 bodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )
        BDSText(
            text = "바이올낫 bodyMedium",
            style = MaterialTheme.typography.bodyMedium
        )
        BDSText(
            text = "바이올낫 bodySmall",
            style = MaterialTheme.typography.bodySmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        BDSText(
            text = "바이올낫 displayLarge",
            style = MaterialTheme.typography.displayLarge
        )
        BDSText(
            text = "바이올낫 displayMedium",
            style = MaterialTheme.typography.displayMedium
        )
        BDSText(
            text = "바이올낫 displaySmall",
            style = MaterialTheme.typography.displaySmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        BDSText(
            text = "바이올낫 headlineLarge",
            style = MaterialTheme.typography.headlineLarge
        )
        BDSText(
            text = "바이올낫 headlineMedium",
            style = MaterialTheme.typography.headlineMedium
        )
        BDSText(
            text = "바이올낫 headlineSmall",
            style = MaterialTheme.typography.headlineSmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        BDSText(
            text = "바이올낫 labelLarge",
            style = MaterialTheme.typography.labelLarge
        )
        BDSText(
            text = "바이올낫 labelMedium",
            style = MaterialTheme.typography.labelMedium
        )
        BDSText(
            text = "바이올낫 labelSmall",
            style = MaterialTheme.typography.labelSmall
        )
    }
}