package com.ddd.component.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

object BDSColor {
    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650a4)
    val PurpleGrey40 = Color(0xFF625b71)
    val Pink40 = Color(0xFF7D5260)

    val Black = Color(0xFF000000)
    val White = Color(0xFFFFFFFF)
    val Red = Color(0xFFF43F5E)
    val Blue = Color(0xFF3B82F6)
    val Green = Color(0xFF10B981)

    val Primary50 = Color(0xFFFBF5FF)
    val Primary100 = Color(0xFFF5E8FF)
    val Primary200 = Color(0xFFEDD5FF)
    val Primary300 = Color(0xFFDFB5FD)
    val Primary400 = Color(0xFFC16EFA)
    val Primary500 = Color(0xFFB757F5)
    val Primary600 = Color(0xFFA435E8)
    val Primary700 = Color(0xFF8E24CC)
    val Primary800 = Color(0xFF7722A7)
    val Primary900 = Color(0xFF621D86)
    val Primary950 = Color(0xFF430863)

    val Secondary50 = Color(0xFFFAFFE5)
    val Secondary100 = Color(0xFFF0FFC8)
    val Secondary200 = Color(0xFFDAFF7D)
    val Secondary300 = Color(0xFFCAFB5B)
    val Secondary400 = Color(0xFFB3F229)
    val Secondary500 = Color(0xFF93D80A)
    val Secondary600 = Color(0xFF72AD03)
    val Secondary700 = Color(0xFF568308)
    val Secondary800 = Color(0xFF46670D)
    val Secondary900 = Color(0xFF3A5710)
    val Secondary950 = Color(0xFF1D3102)

    val Background100 = Color(0xFF101013)
    val Background200 = Color(0xFF17171B)
    val Background300 = Color(0xFF202027)
    val Background400 = Color(0xFF2C2C35)

    val SlateGray100 = Color(0xFFF9FAFB)
    val SlateGray200 = Color(0xFFF2F4F6)
    val SlateGray300 = Color(0xFFE5E8EB)
    val SlateGray400 = Color(0xFFD1D5DB)
    val SlateGray500 = Color(0xFFB0B7C1)
    val SlateGray600 = Color(0xFF6B7484)
    val SlateGray700 = Color(0xFF4E5768)
    val SlateGray800 = Color(0xFF333B4B)
    val SlateGray900 = Color(0xFF191E28)

    val Gray50 = Color(0xFFFAFAFA)
    val Gray100 = Color(0xFFF4F4F5)
    val Gray200 = Color(0xFFE4E4E7)
    val Gray300 = Color(0xFFD4D4D8)
    val Gray400 = Color(0xFFA1A1AA)
    val Gray500 = Color(0xFF71717A)
    val Gray600 = Color(0xFF52525B)
    val Gray700 = Color(0xFF3F3F46)
    val Gray800 = Color(0xFF27272A)
    val Gray900 = Color(0xFF18181B)
    val Gray950 = Color(0xFF09090B)

    @Composable
    fun Int.toComposeColor(): Color = Color(LocalContext.current.getColor(this))
}

object BDSAlpha {
    val AlphaFC = 100F
    val AlphaFA = 99F
    val AlphaF7 = 98F
    val AlphaF5 = 97F
    val AlphaF2 = 96F
    val AlphaF0 = 95F
    val AlphaED = 94F
    val AlphaEB = 93F
    val AlphaE8 = 92F
    val AlphaE6 = 91F
    val AlphaE3 = 90F
    val AlphaE0 = 89F
    val AlphaDE = 88F
    val AlphaDB = 87F
    val AlphaD9 = 86F
    val AlphaD6 = 85F
    val AlphaD4 = 84F
    val AlphaD1 = 83F
    val AlphaCF = 82F
    val AlphaCC = 81F
    val AlphaC9 = 80F
    val AlphaC7 = 79F
    val AlphaC4 = 78F
    val AlphaC2 = 77F
    val AlphaBF = 76F
    val AlphaBD = 75F
    val AlphaBA = 74F
    val AlphaB8 = 73F
    val AlphaB5 = 72F
    val AlphaB3 = 71F
    val AlphaB0 = 70F
    val AlphaAD = 69F
    val AlphaAB = 68F
    val AlphaA8 = 67F
    val AlphaA6 = 66F
    val AlphaA3 = 65F
    val AlphaA1 = 64F
    val Alpha9E = 63F
    val Alpha9C = 62F
    val Alpha99 = 61F
    val Alpha96 = 60F
    val Alpha94 = 59F
    val Alpha91 = 57F
    val Alpha8F = 56F
    val Alpha8C = 56F
    val Alpha8A = 55F
    val Alpha87 = 54F
    val Alpha85 = 53F
    val Alpha82 = 52F
    val Alpha80 = 51F
    val Alpha7D = 50F
    val Alpha7A = 49F
    val Alpha78 = 48F
    val Alpha75 = 47F
    val Alpha73 = 46F
    val Alpha70 = 45F
    val Alpha6E = 44F
    val Alpha6B = 43F
    val Alpha69 = 42F
    val Alpha66 = 41F
    val Alpha63 = 40F
    val Alpha61 = 39F
    val Alpha5E = 38F
    val Alpha5C = 37F
    val Alpha59 = 36F
    val Alpha57 = 35F
    val Alpha54 = 34F
    val Alpha52 = 33F
    val Alpha4F = 32F
    val Alpha4D = 31F
    val Alpha4A = 30F
    val Alpha47 = 29F
    val Alpha45 = 28F
    val Alpha42 = 27F
    val Alpha40 = 26F
    val Alpha3D = 25F
    val Alpha3B = 24F
    val Alpha38 = 23F
    val Alpha36 = 22F
    val Alpha33 = 21F
    val Alpha30 = 20F
    val Alpha2E = 19F
    val Alpha2B = 18F
    val Alpha29 = 17F
    val Alpha26 = 16F
    val Alpha24 = 15F
    val Alpha21 = 14F
    val Alpha1F = 13F
    val Alpha1C = 12F
    val Alpha1A = 11F
    val Alpha17 = 10F
    val Alpha14 = 9F
    val Alpha12 = 8F
    val Alpha0F = 7F
    val Alpha0D = 6F
    val Alpha0A = 5F
    val Alpha08 = 4F
    val Alpha05 = 3F
    val Alpha03 = 2F
    val Alpha00 = 1F

    fun String.toHexAlphaToFloat(): Float = this.toInt(16) / 255f
}

