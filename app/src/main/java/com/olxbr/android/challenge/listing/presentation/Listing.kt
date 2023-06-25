package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Listing(
    state: ListingState.Success,
    onAction: (ListingAction) -> Unit = {}
) {

    val focusManager = LocalFocusManager.current

    Column {

        val queryValue = remember { mutableStateOf(state.query ?: "") }

        //trigger filter function and shows all ads when query is empty
        LaunchedEffect(queryValue.value) {
            if (queryValue.value.isBlank()) {
                onAction(ListingAction.Filter(""))
            }
        }

        OutlinedTextField(
            value = queryValue.value,
            onValueChange = { queryValue.value = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text(text = "Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions( onSearch = {
                onAction(ListingAction.Filter(queryValue.value))
                focusManager.clearFocus()
            }),


            )
        LazyColumn {
            items(state.ads) { ad ->
                AdCard(ad)
            }

            if (state.ads.isEmpty()) {
                item {
                    Text(
                        text = "Nenhum resultado encontrado.",
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
