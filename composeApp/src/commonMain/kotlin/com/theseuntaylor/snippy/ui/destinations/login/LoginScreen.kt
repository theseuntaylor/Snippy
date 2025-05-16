package com.theseuntaylor.snippy.ui.destinations.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theseuntaylor.snippy.ui.components.SnippyButton
import com.theseuntaylor.snippy.ui.components.SnippyEndTextButton
import com.theseuntaylor.snippy.ui.components.SnippyFillSpacer
import com.theseuntaylor.snippy.ui.components.SnippyLogo
import com.theseuntaylor.snippy.ui.components.SnippyVerticalSpacer
import com.theseuntaylor.snippy.ui.destinations.shared.PasswordTextInputField
import com.theseuntaylor.snippy.ui.destinations.shared.TextInput
import com.theseuntaylor.snippy.ui.destinations.shared.TextInputField
import com.theseuntaylor.snippy.ui.model.InputFieldError
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import org.jetbrains.compose.resources.stringResource
import snippy.composeapp.generated.resources.Res
import snippy.composeapp.generated.resources.email_address
import snippy.composeapp.generated.resources.forgot_password
import snippy.composeapp.generated.resources.login
import snippy.composeapp.generated.resources.password

@OptIn(FlowPreview::class)
@Composable
internal fun LoginScreen(modifier: Modifier = Modifier) {
    var emailInput by remember { mutableStateOf("") }
    val passwordState = rememberTextFieldState()
    var passwordError by remember { mutableStateOf(InputFieldError.Initial) }

    val isSubmitEnabled by remember {
        derivedStateOf {
            emailInput.isNotBlank() && passwordState.text.length >= 8
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { passwordState.text }
            .drop(count = 2)
            .debounce(timeoutMillis = 500)
            .collect {
                passwordError = if (it.length < 8) {
                    InputFieldError(
                        shouldShowError = true,
                        message = "Enter at least 8 characters",
                    )
                } else {
                    InputFieldError.Initial
                }
            }
    }

    Column(
        modifier = modifier.padding(
            horizontal = 20.dp,
            vertical = 40.dp,
        ).fillMaxSize(),
    ) {
        LoginContent(
            emailInput = emailInput,
            passwordState = passwordState,
            passwordError = passwordError,
            isSubmitEnabled = isSubmitEnabled,
            onEmailChange = { emailInput = it },
        )

    }
}

@Composable
private fun ColumnScope.LoginContent(
    emailInput: String,
    passwordState: TextFieldState,
    passwordError: InputFieldError,
    isSubmitEnabled: Boolean,
    onEmailChange: (String) -> Unit,
) {
    SnippyLogo()

    SnippyVerticalSpacer(height = 38.dp)

    TextInput(
        sectionTitle = stringResource(resource = Res.string.email_address),
        showErrorMessage = true,
        errorMessage = "Enter a valid email address",
        inputField = {
            TextInputField(
                value = emailInput,
                sectionTitle = stringResource(resource = Res.string.email_address),
                onValueChange = onEmailChange,
            )
        },
    )

    TextInput(
        sectionTitle = stringResource(resource = Res.string.password),
        showErrorMessage = passwordError.shouldShowError,
        errorMessage = "Enter at least 8 characters",
        modifier = Modifier.padding(vertical = 20.dp),
        inputField = { PasswordTextInputField(passwordState = passwordState) },
    )

    SnippyEndTextButton(
        onClick = { /*TODO*/ },
        textContent = stringResource(resource = Res.string.forgot_password),
        style = MaterialTheme.typography.bodySmall,
    )

    SnippyFillSpacer()

    SnippyButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        enabled = isSubmitEnabled,
        textContent = stringResource(resource = Res.string.login),
        containerColor = MaterialTheme.colorScheme.secondary,
    )
}
