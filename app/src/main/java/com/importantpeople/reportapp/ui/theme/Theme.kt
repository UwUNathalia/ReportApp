package com.importantpeople.reportapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrandBlue,
    onPrimary = Color.White,
    primaryContainer = BrandBlueLight,
    onPrimaryContainer = BrandBlueDark,
    secondary = BrandGreen,
    onSecondary = Color.White,
    tertiary = BrandYellow,
    error = BrandRed,
    background = Color.White,
    surface = Color.White
)

/** Tema de la aplicación (Material 3) con los colores de Report App. */
@Composable
fun ReportAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
