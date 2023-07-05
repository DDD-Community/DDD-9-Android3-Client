package com.ddd.component.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ddd.component.R


internal val BDSKorFontFamily = FontFamily(
    Font(R.font.font_thin, FontWeight.Light),
    Font(R.font.font_regular, FontWeight.Normal),
    Font(R.font.font_regular, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.font_regular, FontWeight.Medium),
    Font(R.font.font_semibold, FontWeight.SemiBold),
    Font(R.font.font_bold, FontWeight.Bold),
)

internal val BDSEngFontFamily = FontFamily(
    Font(R.font.font_thin_en, FontWeight.Light),
    Font(R.font.font_regular_en, FontWeight.Normal),
    Font(R.font.font_regular_en, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.font_regular_en, FontWeight.Medium),
    Font(R.font.font_semibold_en, FontWeight.SemiBold),
    Font(R.font.font_bold_en, FontWeight.Bold),
)

sealed class BDSFontFamily {

    open val fontFamily: FontFamily = BDSKorFontFamily

    object Korean : BDSFontFamily()

    object English : BDSFontFamily() {
        override val fontFamily: FontFamily = BDSEngFontFamily
    }
}

private val defaultTypography = Typography()

val BDSTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    displayMedium = defaultTypography.displayMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    displaySmall = defaultTypography.displaySmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),

    headlineLarge = defaultTypography.headlineLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    headlineMedium = defaultTypography.headlineMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    headlineSmall = defaultTypography.headlineSmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    titleLarge = defaultTypography.titleLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = defaultTypography.titleSmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),

    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),

    labelLarge = defaultTypography.labelLarge.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp
    ),
    labelMedium = defaultTypography.labelMedium.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 20.sp
    ),
    labelSmall = defaultTypography.labelSmall.copy(
        fontFamily = BDSKorFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 18.sp
    )
)
