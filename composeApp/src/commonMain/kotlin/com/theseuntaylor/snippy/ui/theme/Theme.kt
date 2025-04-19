package com.theseuntaylor.snippy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.theseuntaylor.snippy.ui.composehelper.shouldUseDarkTheme

private val mediumContrastLightColorScheme = lightColorScheme()
private val mediumContrastDarkColorScheme = darkColorScheme()

@Composable
fun SnippyTheme(
    androidStatusBarSideEffect: @Composable ((statusBarColor: Int, isDarkTheme: Boolean) -> Unit)? = null,
    useDarkTheme: Boolean? = null,
    content: @Composable () -> Unit,
) {

    val shouldUseDarkTheme = useDarkTheme ?: shouldUseDarkTheme()
    val colorScheme =
        if (shouldUseDarkTheme) mediumContrastDarkColorScheme else mediumContrastLightColorScheme

    androidStatusBarSideEffect?.let {
        it(colorScheme.secondary.toArgb(), shouldUseDarkTheme)
    }

    MaterialTheme(
        colorScheme = colorScheme,
    ) {
        Surface { content() }
    }
}
