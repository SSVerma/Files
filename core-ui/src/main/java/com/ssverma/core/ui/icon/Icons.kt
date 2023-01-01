package com.ssverma.core.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

val AppIcons = Icons.Default

sealed interface IconResource {
    data class VectorResource(
        val imageVector: ImageVector
    ) : IconResource

    data class DrawableResource(
        @DrawableRes val resId: Int
    ) : IconResource
}

@Composable
fun IconResource.asIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) {
    when (this) {
        is IconResource.DrawableResource -> {
            Icon(
                painter = painterResource(id = resId),
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier
            )
        }
        is IconResource.VectorResource -> {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier
            )
        }
    }
}