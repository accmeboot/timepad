package com.accme.timepad.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple500,
    secondary = Black050,

    background = Black200,
    onBackground = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    secondary = Color.White,

    background = Purple100,
    onBackground = Color.Black,
)

@Composable
fun TimepadTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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