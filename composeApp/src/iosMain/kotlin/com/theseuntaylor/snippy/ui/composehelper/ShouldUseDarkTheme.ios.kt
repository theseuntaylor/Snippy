package com.theseuntaylor.snippy.ui.composehelper

import androidx.compose.runtime.Composable
import platform.posix.fabs

@Composable
actual fun shouldUseDarkTheme(): Boolean {
    // TODO: handle implementation of dark theme for iOS
    /*
    val uiStyle = UIScreen.mainScreen.traitCollection.userInterfaceStyle
    return uiStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
}*/
    return false
}