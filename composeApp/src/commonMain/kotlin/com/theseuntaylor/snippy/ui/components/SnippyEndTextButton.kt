package com.theseuntaylor.snippy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
internal fun SnippyEndTextButton(
    onClick: () -> Unit,
    textContent: String,
    style: TextStyle = LocalTextStyle.current,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        SnippyFillSpacer()
        Text(
            text = textContent,
            style = style,
            modifier = Modifier.clickable(onClick = onClick),
        )
    }
}