package com.theseuntaylor.snippy.ui.destinations.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.theseuntaylor.snippy.ui.components.SnippyButton
import com.theseuntaylor.snippy.ui.components.SnippyVerticalSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import snippy.composeapp.generated.resources.Res
import snippy.composeapp.generated.resources.app_name
import snippy.composeapp.generated.resources.compose_multiplatform
import snippy.composeapp.generated.resources.create_account
import snippy.composeapp.generated.resources.features_only_available_with_an_account
import snippy.composeapp.generated.resources.login
import snippy.composeapp.generated.resources.privacy_policy
import snippy.composeapp.generated.resources.shorten
import snippy.composeapp.generated.resources.shorten_your_links
import snippy.composeapp.generated.resources.simplify_long_urls
import snippy.composeapp.generated.resources.url_hint

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    var urlInput by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(
            horizontal = 20.dp,
            vertical = 40.dp,
        ).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "App Icon",
                modifier = Modifier.size(size = 24.dp),
            )
            Text(
                text = stringResource(resource = Res.string.app_name),
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(resource = Res.string.shorten_your_links),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            SnippyVerticalSpacer(height = 38.dp)
            Text(
                text = stringResource(Res.string.simplify_long_urls),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(resource = Res.string.features_only_available_with_an_account),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )
            SnippyVerticalSpacer(height = 55.dp)
            OutlinedTextField(
                value = urlInput,
                onValueChange = { urlInput = it },
                placeholder = {
                    Text(
                        text = stringResource(resource = Res.string.url_hint),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
            SnippyVerticalSpacer(height = 14.dp)
            SnippyButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                textContent = stringResource(resource = Res.string.shorten),
                containerColor = MaterialTheme.colorScheme.primary,
                textPadding = 12.dp,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SnippyButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(weight = 1f),
                textContent = stringResource(resource = Res.string.login),
                containerColor = MaterialTheme.colorScheme.secondary,
            )
            SnippyButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(weight = 1f),
                textContent = stringResource(resource = Res.string.create_account),
                containerColor = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = stringResource(resource = Res.string.privacy_policy),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}