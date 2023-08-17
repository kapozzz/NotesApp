package com.example.notes.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = md_lightYellow,
    background = Color.White,
    onBackground = Color.Black,
    secondary = md_lightGray,
    onSecondary = Color.Black,
    tertiary = md_darkWhite
)


private val DarkColors = darkColorScheme(
    primary = md_lightYellow,
    background = md_black,
    onBackground = Color.White,
    secondary = md_lightBlack,
    onSecondary = Color.White,
    tertiary = md_lightBlack
)

@Composable
fun NotesAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
