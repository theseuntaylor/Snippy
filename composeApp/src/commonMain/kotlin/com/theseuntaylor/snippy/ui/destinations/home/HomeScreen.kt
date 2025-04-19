package com.theseuntaylor.snippy.ui.destinations.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import snippy.composeapp.generated.resources.Res
import snippy.composeapp.generated.resources.features_only_available_with_an_account
import snippy.composeapp.generated.resources.shorten_your_links
import snippy.composeapp.generated.resources.simplify_long_urls

@Composable
internal fun HomeScreen(

) {
    Column {
        // app icon
        Row {

        }

        Text(
            text = stringResource(Res.string.shorten_your_links),
            style = MaterialTheme.typography.displaySmall
        )

        Column{
            Text(
                text = stringResource(Res.string.simplify_long_urls),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(Res.string.features_only_available_with_an_account),
                style = MaterialTheme.typography.bodySmall
            )
        }
        // text 3
        Box {  }
        // textfield
        Box {  }
        //button
        Box {  }
        // log in and sign up buttons
        Box {  }
        // copyright
        Box{

        }
    }
}