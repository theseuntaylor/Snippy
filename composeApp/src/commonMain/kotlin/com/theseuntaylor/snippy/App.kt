package com.theseuntaylor.snippy

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.theseuntaylor.snippy.ui.destinations.home.HomeScreen
import com.theseuntaylor.snippy.ui.theme.SnippyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SnippyTheme {
        var showContent by remember { mutableStateOf(false) }
            HomeScreen()
    }
}