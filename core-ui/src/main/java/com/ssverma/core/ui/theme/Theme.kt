package com.ssverma.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkGreen200,
    primaryVariant = darkGreen800,
    secondary = yellow200,
    background = gray900,
    surface = gray800,
    onPrimary = gray900,
    onSecondary = gray900,
    onBackground = Color.White,
    onSurface = white100,
)

private val LightColorPalette = lightColors(
    primary = blue600,
    primaryVariant = blue800,
    secondary = green600,
    background = Color.White,
    surface = white100,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = gray900,
    onSurface = gray900,
)

@Composable
fun FilesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}