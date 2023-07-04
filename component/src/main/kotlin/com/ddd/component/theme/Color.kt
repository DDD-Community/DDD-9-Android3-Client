package com.ddd.component.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

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
val Gray950 = Color(0xFF0D0D0E)

@Composable
fun Int.toComposeColor(): Color = Color(LocalContext.current.getColor(this))
