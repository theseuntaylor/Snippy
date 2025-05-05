package com.theseuntaylor.snippy.ui.destinations.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theseuntaylor.snippy.ui.components.ComposableParam
import com.theseuntaylor.snippy.ui.components.SnippyFillSpacer
import com.theseuntaylor.snippy.ui.components.SnippyVerticalSpacer
import com.theseuntaylor.snippy.ui.theme.SnippyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TextInput(
    sectionTitle: String,
    showErrorMessage: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    inputField: ComposableParam,
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = sectionTitle,
                style = MaterialTheme.typography.bodyMedium,
            )

            SnippyFillSpacer()

            if (showErrorMessage && errorMessage != null) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        SnippyVerticalSpacer(height = 4.dp)

        inputField()
    }
}

@Composable
internal fun TextInputField(
    value: String,
    sectionTitle: String,
    onValueChange: (String) -> Unit,
    trailingIcon: ComposableParam? = null,
) {
    OutlinedTextField(
        value = value,
        shape = RoundedCornerShape(size = 22.dp),
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = sectionTitle,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
            )
        },
        trailingIcon = trailingIcon,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun TextInputPreview() {
    SnippyTheme {
        TextInput(
            sectionTitle = "Email Address",
            showErrorMessage = true,
            errorMessage = "Enter a valid email address",
            inputField = {
                TextInputField(
                    value = "",
                    sectionTitle = "Email Address",
                    onValueChange = {},
                )
            }
        )
    }
}