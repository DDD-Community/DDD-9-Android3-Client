package com.ddd.component.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ddd.component.theme.BDSColor.Pink40
import com.ddd.component.theme.BDSColor.Pink80
import com.ddd.component.theme.BDSColor.Purple40
import com.ddd.component.theme.BDSColor.Purple80
import com.ddd.component.theme.BDSColor.PurpleGrey40
import com.ddd.component.theme.BDSColor.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun BuyOrNotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    darkStatusBar: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            /*if (darkTheme) dynamicDarkColorScheme(context) else */dynamicLightColorScheme(context)
        }

        // darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = (if (darkStatusBar) Color.Black else Color.White).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkStatusBar
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BDSTypography,
        content = {
            CompositionLocalProvider() {
                content.invoke()
            }
        },
    )
}

object ColorRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = BDSColor.Primary50

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 1.0f,
        focusedAlpha = 1.0f,
        hoveredAlpha = 1.0f,
        pressedAlpha = 1.0f,
    )
}

@Composable
fun ColorRipple(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRippleTheme provides ColorRippleTheme) {
        content()
    }
}
