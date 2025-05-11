package com.theseuntaylor.snippy.ui.destinations.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theseuntaylor.snippy.ui.components.SnippyButton
import com.theseuntaylor.snippy.ui.destinations.shared.PasswordTextInputField
import com.theseuntaylor.snippy.ui.destinations.shared.TextInput
import com.theseuntaylor.snippy.ui.model.InputFieldError
import com.theseuntaylor.snippy.ui.destinations.shared.TextInputField
import org.jetbrains.compose.resources.stringResource
import snippy.composeapp.generated.resources.Res
import snippy.composeapp.generated.resources.create_account
import snippy.composeapp.generated.resources.email_address
import snippy.composeapp.generated.resources.first_name
import snippy.composeapp.generated.resources.last_name
import snippy.composeapp.generated.resources.password
import snippy.composeapp.generated.resources.valid_first_name
import snippy.composeapp.generated.resources.valid_last_name


@Composable
fun SignupScreen(modifier: Modifier = Modifier) {
    var firstNameInput by remember { mutableStateOf("") }
    var lastNameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    val passwordState = rememberTextFieldState()
    val confirmPasswordState = rememberTextFieldState()
    val passwordError by remember { mutableStateOf(InputFieldError.Initial) }

    val isButtonEnabled by remember {
        derivedStateOf {
            firstNameInput.isNotBlank()
                    && lastNameInput.isNotBlank()
                    && emailInput.isNotBlank()
                    && passwordState.text.length >= 8
                    && confirmPasswordState.text.length >= 8
        }
    }

    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 40.dp,
        ).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        SignupScreenContent(
            firstNameInput = firstNameInput,
            lastNameInput = lastNameInput,
            emailInput = emailInput,
            passwordState = passwordState,
            confirmPasswordState = confirmPasswordState,
            passwordError = passwordError,
            onEmailChange = { emailInput = it },
            onFirstNameChange = { firstNameInput = it },
            onLastNameChange = { lastNameInput = it },
            isButtonEnabled = isButtonEnabled
        )
    }

}

@Composable
private fun ColumnScope.SignupScreenContent(
    firstNameInput: String,
    lastNameInput: String,
    emailInput: String,
    passwordState: TextFieldState,
    confirmPasswordState: TextFieldState,
    passwordError: InputFieldError,
    isButtonEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
) {

    Text(
        stringResource(Res.string.create_account),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
    )

    // could mod this to have ime actions for keyboard.
    TextInput(
        sectionTitle = stringResource(resource = Res.string.first_name),
        showErrorMessage = true,
        errorMessage = stringResource(Res.string.valid_first_name),
        inputField = {
            TextInputField(
                value = firstNameInput,
                sectionTitle = stringResource(Res.string.first_name),
                onValueChange = onFirstNameChange,
            )
        })

    TextInput(
        sectionTitle = stringResource(resource = Res.string.last_name),
        showErrorMessage = true,
        errorMessage = stringResource(Res.string.valid_last_name),
        inputField = {
            TextInputField(
                value = lastNameInput,
                sectionTitle = stringResource(Res.string.last_name),
                onValueChange = onLastNameChange,
            )
        })

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

    TextInput(
        sectionTitle = stringResource(resource = Res.string.password),
        showErrorMessage = passwordError.shouldShowError,
        errorMessage = "Enter at least 8 characters",
        modifier = Modifier.padding(vertical = 20.dp),
        inputField = { PasswordTextInputField(passwordState = confirmPasswordState) },
    )

    SnippyButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        enabled = isButtonEnabled,
        textContent = stringResource(resource = Res.string.create_account),
        containerColor = MaterialTheme.colorScheme.primary,
    )
}