package com.theseuntaylor.snippy.ui.destinations.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theseuntaylor.snippy.core.utils.toErrorMessage
import com.theseuntaylor.snippy.data.source.remote.dto.auth.LoginRequestDto
import com.theseuntaylor.snippy.extensions.isEmailValid
import com.theseuntaylor.snippy.ui.components.SnippyButton
import com.theseuntaylor.snippy.ui.components.SnippyEndTextButton
import com.theseuntaylor.snippy.ui.components.SnippyFillSpacer
import com.theseuntaylor.snippy.ui.components.SnippyVerticalSpacer
import com.theseuntaylor.snippy.ui.destinations.shared.PasswordTextInputField
import com.theseuntaylor.snippy.ui.destinations.shared.TextInput
import com.theseuntaylor.snippy.ui.destinations.shared.TextInputField
import com.theseuntaylor.snippy.ui.model.InputFieldError
import com.theseuntaylor.snippy.ui.viewmodels.AuthViewmodel
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
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    authViewmodel: AuthViewmodel,
    onLoginSuccess: () -> Unit,
) {
    var emailInput by remember { mutableStateOf("") }
    val passwordState = rememberTextFieldState()
    var passwordError by remember { mutableStateOf(InputFieldError.Initial) }
    var emailError by remember { mutableStateOf(InputFieldError.Initial) }

    val uiState by authViewmodel.loginUiState.collectAsStateWithLifecycle()

    val isSubmitEnabled by remember {
        derivedStateOf {
            emailInput.isNotBlank() && emailInput.isEmailValid() && passwordState.text.length >= 8
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success) {
            onLoginSuccess()
            authViewmodel.resetLoginUiState()
        }
    }

    InputFieldErrorsEffect(
        emailInput = emailInput,
        passwordState = passwordState,
        onEmailErrorChange = { emailError = it },
        onPasswordErrorChange = { passwordError = it },
    )

    Column(
        modifier = modifier.padding(
            horizontal = 20.dp,
            vertical = 40.dp,
        ).fillMaxSize(),
    ) {
        LoginContent(
            uiState = uiState,
            emailInput = emailInput,
            passwordState = passwordState,
            passwordError = passwordError,
            emailError = emailError,
            isSubmitEnabled = isSubmitEnabled,
            onEmailChange = { emailInput = it },
            onLoginClick = {
                val loginRequestDto = LoginRequestDto(
                    email = emailInput,
                    password = passwordState.text.toString(),
                )
                authViewmodel.loginUser(loginRequestDto = loginRequestDto)
            },
        )

    }
}

@OptIn(FlowPreview::class)
@Composable
private fun InputFieldErrorsEffect(
    emailInput: String,
    passwordState: TextFieldState,
    onEmailErrorChange: (InputFieldError) -> Unit,
    onPasswordErrorChange: (InputFieldError) -> Unit,
) {

    LaunchedEffect(Unit) {
        snapshotFlow { passwordState.text }
            .drop(count = 2)
            .debounce(timeoutMillis = 500)
            .collect {
                val passwordError = if (it.length < 8) {
                    InputFieldError(
                        shouldShowError = true,
                        message = "Enter at least 8 characters",
                    )
                } else {
                    InputFieldError.Initial
                }
                onPasswordErrorChange(passwordError)
            }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { emailInput }
            .drop(count = 2)
            .debounce(timeoutMillis = 500)
            .collect {
                val emailError = if (!it.isEmailValid()) {
                    InputFieldError(
                        shouldShowError = true,
                        message = "Enter a valid email address",
                    )
                } else {
                    InputFieldError.Initial
                }
                onEmailErrorChange(emailError)
            }
    }

}

@Composable
private fun ColumnScope.LoginContent(
    uiState: LoginUiState,
    emailInput: String,
    passwordState: TextFieldState,
    passwordError: InputFieldError,
    emailError: InputFieldError,
    isSubmitEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Text(
        text = stringResource(Res.string.login),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
    )

    SnippyVerticalSpacer(height = 38.dp)

    TextInput(
        sectionTitle = stringResource(resource = Res.string.email_address),
        showErrorMessage = emailError.shouldShowError,
        errorMessage = emailError.message,
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
        errorMessage = passwordError.message,
        modifier = Modifier.padding(vertical = 20.dp),
        inputField = { PasswordTextInputField(passwordState = passwordState) },
    )

    SnippyEndTextButton(
        onClick = { /*TODO*/ },
        textContent = stringResource(resource = Res.string.forgot_password),
        style = MaterialTheme.typography.bodySmall,
    )

    SnippyFillSpacer()

    if (uiState == LoginUiState.Loading) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(width = 64.dp)
                .align(alignment = Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else if (uiState is LoginUiState.Error) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            text = uiState.appError.toErrorMessage(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
        )
    }

    SnippyVerticalSpacer(height = 32.dp)

    SnippyButton(
        onClick = onLoginClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = isSubmitEnabled,
        textContent = stringResource(resource = Res.string.login),
        containerColor = MaterialTheme.colorScheme.secondary,
    )
}
