package com.ddd.component.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.component.BDSText
import com.ddd.component.theme.Background100
import com.ddd.component.theme.Background200
import com.ddd.component.theme.Background300
import com.ddd.component.theme.Background400
import com.ddd.component.theme.Blue
import com.ddd.component.theme.Gray100
import com.ddd.component.theme.Gray200
import com.ddd.component.theme.Gray300
import com.ddd.component.theme.Gray400
import com.ddd.component.theme.Gray50
import com.ddd.component.theme.Gray500
import com.ddd.component.theme.Gray600
import com.ddd.component.theme.Gray700
import com.ddd.component.theme.Gray800
import com.ddd.component.theme.Gray900
import com.ddd.component.theme.Gray950
import com.ddd.component.theme.Green
import com.ddd.component.theme.Primary100
import com.ddd.component.theme.Primary200
import com.ddd.component.theme.Primary300
import com.ddd.component.theme.Primary400
import com.ddd.component.theme.Primary50
import com.ddd.component.theme.Primary500
import com.ddd.component.theme.Primary600
import com.ddd.component.theme.Primary700
import com.ddd.component.theme.Primary800
import com.ddd.component.theme.Primary900
import com.ddd.component.theme.Primary950
import com.ddd.component.theme.Red
import com.ddd.component.theme.Secondary100
import com.ddd.component.theme.Secondary200
import com.ddd.component.theme.Secondary300
import com.ddd.component.theme.Secondary400
import com.ddd.component.theme.Secondary50
import com.ddd.component.theme.Secondary500
import com.ddd.component.theme.Secondary600
import com.ddd.component.theme.Secondary700
import com.ddd.component.theme.Secondary800
import com.ddd.component.theme.Secondary900
import com.ddd.component.theme.Secondary950
import com.ddd.component.theme.SlateGray100
import com.ddd.component.theme.SlateGray200
import com.ddd.component.theme.SlateGray300
import com.ddd.component.theme.SlateGray400
import com.ddd.component.theme.SlateGray500
import com.ddd.component.theme.SlateGray600

private val backgroundColors = listOf(
    Background100,
    Background200,
    Background300,
    Background400,
)
private val primaryColors = listOf(
    Primary50,
    Primary100,
    Primary200,
    Primary300,
    Primary400,
    Primary500,
    Primary600,
    Primary700,
    Primary800,
    Primary900,
    Primary950
)

private val secondaryColors = listOf(
    Secondary50,
    Secondary100,
    Secondary200,
    Secondary300,
    Secondary400,
    Secondary500,
    Secondary600,
    Secondary700,
    Secondary800,
    Secondary900,
    Secondary950
)

private val slateGrayColors = listOf(
    SlateGray100,
    SlateGray200,
    SlateGray300,
    SlateGray400,
    SlateGray500,
    SlateGray600
)

private val grayColors = listOf(
    Gray50,
    Gray100,
    Gray200,
    Gray300,
    Gray400,
    Gray500,
    Gray600,
    Gray700,
    Gray800,
    Gray900,
    Gray950
)

private val solidColors = listOf(
    Green,
    Blue,
    Red,
)

private val allColors = backgroundColors + primaryColors + secondaryColors + slateGrayColors + grayColors + solidColors

@Composable
@Preview(showBackground = true)
fun ThemeTestScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        allColors.forEach { color ->
            BDSText(
                text = color.toString(), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            )
        }
    }
}