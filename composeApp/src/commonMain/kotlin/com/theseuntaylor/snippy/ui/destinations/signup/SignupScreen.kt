package com.theseuntaylor.snippy.ui.destinations.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.theseuntaylor.snippy.ui.components.SnippyButton
import org.jetbrains.compose.resources.stringResource
import snippy.composeapp.generated.resources.Res
import snippy.composeapp.generated.resources.confirm_password
import snippy.composeapp.generated.resources.create_account
import snippy.composeapp.generated.resources.email
import snippy.composeapp.generated.resources.first_name
import snippy.composeapp.generated.resources.last_name
import snippy.composeapp.generated.resources.password
import snippy.composeapp.generated.resources.url_hint

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
) {

    var firstNameInput by remember { mutableStateOf("") }
    var lastNameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var confirmPasswordInput by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(
            horizontal = 20.dp,
            vertical = 40.dp,
        ).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            stringResource(Res.string.create_account),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Column {
            Text("First Name")
            OutlinedTextField(
                value = firstNameInput,
                onValueChange = { firstNameInput = it },
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.first_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }

        Column {
            Text("Last Name")
            OutlinedTextField(
                value = lastNameInput,
                onValueChange = { lastNameInput = it },
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.last_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }

        Column {
            Text("Email Address")
            OutlinedTextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.email),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }

        Column {
            Text("Password")
            OutlinedTextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.password),
                        fontWeight = FontWeight.Bold
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        if (passwordVisibility) {
                            Icon(
                                Icons.Filled.Warning,
                                contentDescription = "Toggle password visibility"
                            )
                        } else {
                            Icon(
                                Icons.Filled.Warning,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )
        }

        Column {
            Text("Confirm Password")
            OutlinedTextField(
                value = confirmPasswordInput,
                onValueChange = { confirmPasswordInput = it },
                visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.confirm_password),
                        fontWeight = FontWeight.Bold
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        confirmPasswordVisibility = !confirmPasswordVisibility
                    }) {
                        if (passwordVisibility) {
                            Icon(
                                Icons.Outlined.Warning,
                                contentDescription = "Toggle password visibility"
                            )
                        } else {
                            Icon(
                                Icons.Filled.Warning,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
        }
        SnippyButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            textContent = stringResource(resource = Res.string.create_account),
            containerColor = MaterialTheme.colorScheme.primary,
        )
    }
}