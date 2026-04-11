package com.growzy.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    tertiary = GreenTertiary,
    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = Color(0xFFE8F5E9),
    onPrimary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    onSurfaceVariant = Color(0xFF5F6368),
    outline = Color(0xFFDADCE0),
    error = Error
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenDark,
    secondary = GreenSecondaryDark,
    tertiary = GreenTertiary,
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = Color(0xFF1A2F23),
    onPrimary = Color.Black,
    onBackground = Color(0xFFE3E2E6),
    onSurface = Color(0xFFE3E2E6),
    onSurfaceVariant = Color(0xFFB0B5BA),
    outline = Color(0xFF3C4043),
    error = Error
)

@Composable
fun GrowzyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val animatedPrimary = animateColorAsState(
        targetValue = colorScheme.primary,
        animationSpec = tween(durationMillis = 300),
        label = "primary_animation"
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme.copy(primary = animatedPrimary.value),
        typography = Typography,
        content = content
    )
}