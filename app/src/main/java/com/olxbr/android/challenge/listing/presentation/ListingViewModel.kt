package com.olxbr.android.challenge.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.olxbr.android.challenge.R
import com.olxbr.android.challenge.listing.domain.ListingRepository
import com.olxbr.android.challenge.listing.domain.model.Ad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

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

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository,
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
                _state.update { ListingState.Success(repository.getAds()) }
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
            val normalizedQuery = removeAccents(query)
            val result = if (normalizedQuery.isEmpty()) {
                repository.getAds()
            } else {
                repository.getAds().filter { ad ->
                    val normalizedSubject = removeAccents(ad.subject)
                    normalizedSubject.contains(normalizedQuery, ignoreCase = true)
                }
            }
            _state.update { ListingState.Success(result, query) }
        }
    }

        private fun removeAccents(string: String): String {
        val accentsMap = mapOf(
            'ã' to 'a',
            'á' to 'a',
            'â' to 'a',
            'õ' to 'o',
            'ó' to 'o',
            'ô' to 'o',
            'é' to 'e',
            'ê' to 'e',
            'í' to 'i',
            'ú' to 'u',
        )

        val stringBuilder = StringBuilder()
        for (char in string) {
            val unaccentedChar = accentsMap[char.lowercaseChar()] ?: char
            stringBuilder.append(unaccentedChar)
        }
        return stringBuilder.toString()
    }
}

//class ListingViewModelFactory : ViewModelProvider.NewInstanceFactory() {

 //   override fun <T : ViewModel> create(modelClass: Class<T>): T =
 //       ListingViewModel(ListingService()) as T }
