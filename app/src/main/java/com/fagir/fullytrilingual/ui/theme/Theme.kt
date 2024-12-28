package com.fagir.fullytrilingual.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
    // Puedes agregar o cambiar más colores aquí si gustas
)

/**
 * Tema principal de la app.
 * Se adapta según el modo oscuro o la versión de Android (colores dinámicos en Android 12+).
 */
@Composable
fun FullyTrilingualTheme(
    // Indica si se usa el modo oscuro
    darkTheme: Boolean = isSystemInDarkTheme(),

    // Colores dinámicos, disponibles a partir de Android 12 (API 31)
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Si el dispositivo soporta colores dinámicos y la app los usa
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // Si no, checamos si está en modo oscuro o claro
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // MaterialTheme aplica el esquema de colores y tipografías
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
