package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olxbr.android.challenge.ui.theme.Purple40
import com.olxbr.android.challenge.ui.theme.cardGray


@Composable
fun ListingError(state: ListingState.Error, onAction: (ListingAction) -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White.copy(alpha = 0.9f))
            )
                Column(
                    modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                    state.imageResId?.let { imageResId ->
                    Image(
                        painter = painterResource(id = state.imageResId),
                        contentDescription = "${state.errorMessageResId}",
                        Modifier.size(80.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        stringResource(state.errorMessageResId),
                        textAlign = TextAlign.Center,
                        color = cardGray,
                        fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = { onAction(ListingAction.Initialize) },
                        modifier = Modifier.padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                        containerColor = Purple40,
                        contentColor = Color.White // Change text color
                    )

                ) {
                        Text(text = "Tentar Novamente",
                            fontSize = 16.sp)
                }
            }

        })
}