package com.ddd.component.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.ddd.component.R


val BDSKorFontFamily = FontFamily(
    Font(R.font.pretendard_thin, FontWeight.Light),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.pretendard_regular, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
)

private val defaultTypography = Typography()

/*
 * TODO : 크기, 행간 등은 디자인 시스템 나오면 적용할 예정
 */
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = BDSKorFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = BDSKorFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = BDSKorFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = BDSKorFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = BDSKorFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = BDSKorFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = BDSKorFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = BDSKorFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = BDSKorFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = BDSKorFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = BDSKorFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = BDSKorFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = BDSKorFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = BDSKorFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = BDSKorFontFamily)
)
