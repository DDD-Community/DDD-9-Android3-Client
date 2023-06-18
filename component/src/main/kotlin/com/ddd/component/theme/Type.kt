package com.ddd.component.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.ddd.component.R


val BDSKorFontFamily = FontFamily(
    Font(R.font.font_thin, FontWeight.Light),
    Font(R.font.font_thin, FontWeight.Normal),
    Font(R.font.font_thin, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.font_thin, FontWeight.Medium),
    Font(R.font.font_semibold, FontWeight.SemiBold),
    Font(R.font.font_bold, FontWeight.Bold),
)

private val defaultTypography = Typography()

/*
 * TODO : 크기, 행간 등은 디자인 시스템 나오면 적용할 예정
 */
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    displayMedium = defaultTypography.displayMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    displaySmall = defaultTypography.displaySmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold
    ),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = BDSKorFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = BDSKorFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = BDSKorFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    titleSmall = defaultTypography.titleSmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
    ),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = BDSKorFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = BDSKorFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = BDSKorFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = BDSKorFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = BDSKorFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = BDSKorFontFamily)
)
