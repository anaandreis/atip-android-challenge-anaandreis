package com.olxbr.android.challenge.listing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.olxbr.android.challenge.listing.presentation.ListingScreen
import com.olxbr.android.challenge.listing.presentation.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ListingViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.collectAsState()

            ListingScreen(state = state.value, onAction = viewModel::onAction)
        }
    }
}
