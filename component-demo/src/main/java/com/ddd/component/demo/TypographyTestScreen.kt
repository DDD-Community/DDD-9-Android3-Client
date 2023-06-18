package com.ddd.component.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.component.BDSDivider

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
        Text(
            text = "바이올낫 titleLarge",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "바이올낫 titleMedium",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "바이올낫 titleSmall",
            style = MaterialTheme.typography.titleSmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "바이올낫 bodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "바이올낫 bodyMedium",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "바이올낫 bodySmall",
            style = MaterialTheme.typography.bodySmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "바이올낫 displayLarge",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "바이올낫 displayMedium",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "바이올낫 displaySmall",
            style = MaterialTheme.typography.displaySmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "바이올낫 headlineLarge",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "바이올낫 headlineMedium",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "바이올낫 headlineSmall",
            style = MaterialTheme.typography.headlineSmall
        )

        BDSDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "바이올낫 labelLarge",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "바이올낫 labelMedium",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "바이올낫 labelSmall",
            style = MaterialTheme.typography.labelSmall
        )
    }
}