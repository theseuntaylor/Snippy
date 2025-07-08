package com.theseuntaylor.snippy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val applicationComponent = (applicationContext as SnippyApplication).component

        setContent {
            applicationComponent.snippyApp
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}