package com.ddd.component.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor.Background100
import com.ddd.component.theme.BDSColor.Background200
import com.ddd.component.theme.BDSColor.Background300
import com.ddd.component.theme.BDSColor.Background400
import com.ddd.component.theme.BDSColor.Blue
import com.ddd.component.theme.BDSColor.Gray100
import com.ddd.component.theme.BDSColor.Gray200
import com.ddd.component.theme.BDSColor.Gray300
import com.ddd.component.theme.BDSColor.Gray400
import com.ddd.component.theme.BDSColor.Gray50
import com.ddd.component.theme.BDSColor.Gray500
import com.ddd.component.theme.BDSColor.Gray600
import com.ddd.component.theme.BDSColor.Gray700
import com.ddd.component.theme.BDSColor.Gray800
import com.ddd.component.theme.BDSColor.Gray900
import com.ddd.component.theme.BDSColor.Gray950
import com.ddd.component.theme.BDSColor.Green
import com.ddd.component.theme.BDSColor.Primary100
import com.ddd.component.theme.BDSColor.Primary200
import com.ddd.component.theme.BDSColor.Primary300
import com.ddd.component.theme.BDSColor.Primary400
import com.ddd.component.theme.BDSColor.Primary50
import com.ddd.component.theme.BDSColor.Primary500
import com.ddd.component.theme.BDSColor.Primary600
import com.ddd.component.theme.BDSColor.Primary700
import com.ddd.component.theme.BDSColor.Primary800
import com.ddd.component.theme.BDSColor.Primary900
import com.ddd.component.theme.BDSColor.Primary950
import com.ddd.component.theme.BDSColor.Red
import com.ddd.component.theme.BDSColor.Secondary100
import com.ddd.component.theme.BDSColor.Secondary200
import com.ddd.component.theme.BDSColor.Secondary300
import com.ddd.component.theme.BDSColor.Secondary400
import com.ddd.component.theme.BDSColor.Secondary50
import com.ddd.component.theme.BDSColor.Secondary500
import com.ddd.component.theme.BDSColor.Secondary600
import com.ddd.component.theme.BDSColor.Secondary700
import com.ddd.component.theme.BDSColor.Secondary800
import com.ddd.component.theme.BDSColor.Secondary900
import com.ddd.component.theme.BDSColor.Secondary950
import com.ddd.component.theme.BDSColor.SlateGray100
import com.ddd.component.theme.BDSColor.SlateGray200
import com.ddd.component.theme.BDSColor.SlateGray300
import com.ddd.component.theme.BDSColor.SlateGray400
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray600
import com.ddd.component.theme.BDSColor.SlateGray700
import com.ddd.component.theme.BDSColor.SlateGray800
import com.ddd.component.theme.BDSColor.SlateGray900

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
    SlateGray600,
    SlateGray700,
    SlateGray800,
    SlateGray900
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

private val allColors = listOf(
    "background" to backgroundColors,
    "primary" to primaryColors,
    "secondary" to secondaryColors,
    "slateGray" to slateGrayColors,
    "gray" to grayColors,
    "solid" to solidColors
)

private fun getContrastColor(background: Color): Color {
    val backgroundLuminance = background.luminance()
    val darkContrastThreshold = 0.15

    return if (backgroundLuminance > darkContrastThreshold) {
        Color.Black
    } else {
        Color.White
    }
}

@Composable
@Preview(showBackground = true)
fun ThemeTestScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        allColors.forEach { (title, colors) ->
            BDSText(
                fontWeight = FontWeight.Bold,
                text = title,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            colors.forEach { color ->
                BDSText(
                    color = getContrastColor(color),
                    text = color.toString(), modifier = Modifier
                        .fillMaxWidth()
                        .background(color)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
