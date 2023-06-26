package com.olxbr.android.challenge.listing.data.remote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import coil.compose.*
import com.olxbr.android.challenge.R

@Composable

fun LoadImageFromRemote(imageUrl: String, imageDescription: String?) {
    SubcomposeAsyncImage(
        model = imageUrl,
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = RectangleShape),
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        contentDescription = imageDescription,
        error = { R.drawable.ic_generic_error_image_24 }
    )
}