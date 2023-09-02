package com.ddd.component.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class BDSTextData(
    val text: String? = null,
    val modifier: Modifier = Modifier,
    val color: Color = Color.Unspecified,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val fontWeight: FontWeight = FontWeight.Normal,
    val letterSpacing: TextUnit = (-0.3).sp,
    val textAlign: TextAlign? = TextAlign.Start,
)