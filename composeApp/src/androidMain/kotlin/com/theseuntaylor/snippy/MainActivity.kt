package com.theseuntaylor.snippy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.theseuntaylor.snippy.ui.theme.SnippyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val applicationComponent = (applicationContext as SnippyApplication).component

        setContent {
            SnippyTheme {
                SnippyApp()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}