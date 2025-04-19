package com.theseuntaylor.snippy.ui.composehelper

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
actual fun shouldUseDarkTheme(): Boolean = isSystemInDarkTheme()