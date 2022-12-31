package com.ssverma.core.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class Illustration(
    @DrawableRes val illustrationResId: Int
)

internal val LocalIllustration = staticCompositionLocalOf<Illustration> {
    error("No LocalIllustration specified")
}