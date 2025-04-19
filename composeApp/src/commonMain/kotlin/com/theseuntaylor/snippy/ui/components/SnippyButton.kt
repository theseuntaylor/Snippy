package com.theseuntaylor.snippy.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SnippyButton(
    onClick: () -> Unit,
    textContent: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    textPadding: Dp = 6.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        content = {
            Text(
                textContent,
                modifier = Modifier.padding(vertical = textPadding),
                // color = Color.White
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        )
    )
}