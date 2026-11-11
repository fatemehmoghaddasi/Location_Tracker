package com.example.locationtracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LightBlue,
    secondary = Brown,
    background = DarkBlue,
    surface = DarkBlue
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = Brown,
    background = White,
    surface = BackgroundBlue,
    onPrimary = White,
    onSecondary = White,
    onBackground = DarkBlue,
    onSurface = DarkBlue


)


@Composable
fun LocationTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}