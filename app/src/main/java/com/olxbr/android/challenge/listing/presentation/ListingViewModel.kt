package com.olxbr.android.challenge.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.olxbr.android.challenge.R
import com.olxbr.android.challenge.listing.model.Ad
import com.olxbr.android.challenge.listing.data.remote.ListingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

sealed class ListingState {

    object Uninitialized : ListingState()

    data class Loading(val query: String? = null) : ListingState()

    data class Success(val ads: List<Ad>, val query: String? = null) : ListingState()

    //feeding error message and image to error screen
    data class Error(val errorMessageResId: Int, val imageResId: Int?) : ListingState()
}

sealed class ListingAction {

    object Initialize : ListingAction()

    data class Filter(val query: String) : ListingAction()
}

class ListingViewModel(
    private val service: ListingService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _state = MutableStateFlow<ListingState>(ListingState.Uninitialized)
    val state: StateFlow<ListingState> = _state

    fun onAction(action: ListingAction) {
        when (action) {
            is ListingAction.Initialize -> initialize()
            is ListingAction.Filter -> filter(action.query)
        }
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {
            _state.update { ListingState.Loading() }
            try {
                _state.update { ListingState.Success(service.getAds()) }
            } catch (e: IOException) {
                _state.update { ListingState.Error(R.string.io_error, R.drawable.ic_io_error_24) }
            } catch (e: JsonParseException) {
                _state.update { ListingState.Error(R.string.json_parse_error, R.drawable.ic_generic_error_image_24) }
            } catch (e: Exception) {
                _state.update { ListingState.Error(R.string.generic_error, R.drawable.ic_generic_error_image_24) }
            }
        }
    }

    private fun filter(query: String) {
        viewModelScope.launch(dispatcher) {
            val result =
                service.getAds().filter { ad -> ad.subject.startsWith(query) }

            _state.update { ListingState.Success(result, query) }
        }
    }
}

class ListingViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ListingViewModel(ListingService()) as T
}
