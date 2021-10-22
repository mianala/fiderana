package com.mianala.fiderana.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Alternative = Color(0xffd64040)
val PrimaryLight = Color(0xffff736b)
val PrimaryDark = Color(0xff9e0019)
val Secondary = Color(0xff2b94d6)


private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = Alternative,
    secondary = Secondary
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    primaryVariant = Alternative,
    secondary = Secondary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FideranaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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