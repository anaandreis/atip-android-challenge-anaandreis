package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olxbr.android.challenge.R
import com.olxbr.android.challenge.ui.theme.Purple40
import com.olxbr.android.challenge.ui.theme.cardGray

@Composable
fun ListingLoadingScreen(state: ListingState.Loading) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White.copy(alpha = 0.9f))
            )
                Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color= Purple40,
                        strokeWidth = 10.dp

                )

                    Spacer(modifier = Modifier.height(42.dp))

                    if(state.query == null) {

                    Text(
                        stringResource(R.string.carregando),
                        color = cardGray,
                        fontSize = 20.sp
                     )
                    } else {

                    Text(
                        "Procurando ${state.query}",
                        color = Color.Black,
                        fontSize = 20.sp)
                }
            }
        }
    )

}

@Preview
@Composable
fun PreviewLoading() {
    ListingLoadingScreen(state = ListingState.Loading(query = null))
}