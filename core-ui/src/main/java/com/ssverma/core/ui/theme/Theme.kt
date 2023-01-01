package com.ssverma.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val filesLightColorScheme = lightColorScheme(
    primary = filesLightPrimary,
    onPrimary = filesLightOnPrimary,
    primaryContainer = filesLightPrimaryContainer,
    onPrimaryContainer = filesLightOnPrimaryContainer,
    secondary = filesLightSecondary,
    onSecondary = filesLightOnSecondary,
    secondaryContainer = filesLightSecondaryContainer,
    onSecondaryContainer = filesLightOnSecondaryContainer,
    tertiary = filesLightTertiary,
    onTertiary = filesLightOnTertiary,
    tertiaryContainer = filesLightTertiaryContainer,
    onTertiaryContainer = filesLightOnTertiaryContainer,
    error = filesLightError,
    errorContainer = filesLightErrorContainer,
    onError = filesLightOnError,
    onErrorContainer = filesLightOnErrorContainer,
    background = filesLightBackground,
    onBackground = filesLightOnBackground,
    surface = filesLightSurface,
    onSurface = filesLightOnSurface,
    surfaceVariant = filesLightSurfaceVariant,
    onSurfaceVariant = filesLightOnSurfaceVariant,
    outline = filesLightOutline,
    inverseOnSurface = filesLightInverseOnSurface,
    inverseSurface = filesLightInverseSurface,
    inversePrimary = filesLightInversePrimary,
    surfaceTint = filesLightSurfaceTint,
    outlineVariant = filesLightOutlineVariant,
    scrim = filesLightScrim,
)


private val filesDarkColorScheme = darkColorScheme(
    primary = filesDarkPrimary,
    onPrimary = filesDarkOnPrimary,
    primaryContainer = filesDarkPrimaryContainer,
    onPrimaryContainer = filesDarkOnPrimaryContainer,
    secondary = filesDarkSecondary,
    onSecondary = filesDarkOnSecondary,
    secondaryContainer = filesDarkSecondaryContainer,
    onSecondaryContainer = filesDarkOnSecondaryContainer,
    tertiary = filesDarkTertiary,
    onTertiary = filesDarkOnTertiary,
    tertiaryContainer = filesDarkTertiaryContainer,
    onTertiaryContainer = filesDarkOnTertiaryContainer,
    error = filesDarkError,
    errorContainer = filesDarkErrorContainer,
    onError = filesDarkOnError,
    onErrorContainer = filesDarkOnErrorContainer,
    background = filesDarkBackground,
    onBackground = filesDarkOnBackground,
    surface = filesDarkSurface,
    onSurface = filesDarkOnSurface,
    surfaceVariant = filesDarkSurfaceVariant,
    onSurfaceVariant = filesDarkOnSurfaceVariant,
    outline = filesDarkOutline,
    inverseOnSurface = filesDarkInverseOnSurface,
    inverseSurface = filesDarkInverseSurface,
    inversePrimary = filesDarkInversePrimary,
    surfaceTint = filesDarkSurfaceTint,
    outlineVariant = filesDarkOutlineVariant,
    scrim = filesDarkScrim,
)

@Composable
fun FilesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    val filesColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> filesDarkColorScheme
        else -> filesLightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        colorScheme = filesColorScheme,
        typography = filesTypography,
        shapes = filesShapes,
        content = content
    )
}