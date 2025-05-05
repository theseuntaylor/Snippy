package com.theseuntaylor.snippy

import androidx.compose.runtime.Composable
import com.theseuntaylor.snippy.ui.destinations.home.HomeScreen
import com.theseuntaylor.snippy.ui.theme.SnippyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SnippyTheme {
        HomeScreen()
    }
}