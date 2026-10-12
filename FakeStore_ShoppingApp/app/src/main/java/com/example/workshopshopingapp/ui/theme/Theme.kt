package com.example.workshopshopingapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LuxuryDarkColorScheme = darkColorScheme(
    primary = AccentOrange,
    onPrimary = Color.White,
    primaryContainer = AccentOrange.copy(alpha = 0.2f),
    onPrimaryContainer = AccentOrangeGlow,
    secondary = AccentGold,
    onSecondary = Color.Black,
    secondaryContainer = AccentGold.copy(alpha = 0.15f),
    onSecondaryContainer = AccentGold,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = DarkSurfaceBorder,
    outlineVariant = DarkSurfaceBorder.copy(alpha = 0.5f)
)

@Composable
fun WORKSHOPSHOPINGAPPTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LuxuryDarkColorScheme,
        typography = Typography,
        content = content
    )
}
