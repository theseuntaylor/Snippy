package com.theseuntaylor.snippy

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.theseuntaylor.snippy.di.DesktopApplicationComponent
import com.theseuntaylor.snippy.ui.theme.SnippyTheme

fun main() = application {
    val applicationComponent = DesktopApplicationComponent::class
    Window(
        onCloseRequest = ::exitApplication,
        title = "Snippy",
    ) {
//        SnippyTheme {
//            SnippyApp(authViewmodel = applicationComponent.)
//        }
    }
}