package com.theseuntaylor.snippy

import androidx.compose.runtime.Composable
import com.theseuntaylor.snippy.ui.theme.SnippyTheme
import me.tatarka.inject.annotations.Inject
import org.jetbrains.compose.ui.tooling.preview.Preview

@Inject
@Composable
@Preview
fun App() {
    SnippyTheme {
        SnippyApp()
    }
}