package com.olxbr.android.challenge

import app.cash.turbine.test
import com.olxbr.android.challenge.listing.data.remote.ListingService
import com.olxbr.android.challenge.listing.model.Ad
import com.olxbr.android.challenge.listing.presentation.ListingAction
import com.olxbr.android.challenge.listing.presentation.ListingState
import com.olxbr.android.challenge.listing.presentation.ListingViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ListingViewModelFilterFunctionTest {

    private lateinit var viewModel: ListingViewModel
    private lateinit var service: ListingService

    @Before
    fun setup() {
        service = mockk()
        viewModel = ListingViewModel(service, Dispatchers.Default)
        coEvery { service.getAds() } returns listOf(
            Ad("Image Url 1", "SOFÁ", "1", "Time", "Location1"),
            Ad("Image Url 2", "soFA", "2", "Time", "Location2"),
            Ad("Image Url 3", "2 sofás!", "3", "Time", "Location3"),
            Ad("Image Url 4", "Cadeira", "4", "Time", "Location4"),
            Ad("Image Url 5", "Conjunto de cadeiras", "5", "Time", "Location5"),

            )
    }


    @Test
    fun shouldFilterAdsSuccessfully_withQueryNotInTheBeginningOfSentence() = runBlocking  {

        viewModel.onAction(ListingAction.Initialize)

        delay(100)

        viewModel.state.test {
            val initialState = awaitItem()
            assertEquals(ListingState.Success::class, initialState::class)
        }

        viewModel.onAction(ListingAction.Filter("cadeira"))
        delay(100)
        viewModel.state.test {
            val expectedAds = listOf(
                Ad("Image Url 4", "Cadeira", "4", "Time", "Location4"),
                Ad("Image Url 5", "Conjunto de cadeiras", "5", "Time", "Location5")
            )
            assertEquals(ListingState.Success(expectedAds, "cadeira"), awaitItem())
        }
    }


    @Test
    fun shouldFilterAdsSuccessfully_withSpecialCharacters() = runBlocking {
        viewModel.onAction(ListingAction.Initialize)
        delay(100)
        // Wait for the ViewModel to transition to the Success state
        viewModel.state.test {
            val initialState = awaitItem()
            assertEquals(ListingState.Success::class, initialState::class)
        }

        viewModel.onAction(ListingAction.Filter("sofa"))
        delay(100)

        viewModel.state.test {
            val expectedAds = listOf(
                Ad("Image Url 1", "SOFÁ", "1", "Time", "Location1"),
                Ad("Image Url 2", "soFA", "2", "Time", "Location2"),
                Ad("Image Url 3", "2 sofás!", "3", "Time", "Location3")
            )
            assertEquals(ListingState.Success(expectedAds, "sofa"), awaitItem())
        }
    }

    @Test
    fun shouldFilterAdsSuccessfully_withMixedCaseCharacters() = runBlocking {

        viewModel.onAction(ListingAction.Initialize)
        delay(100)

        viewModel.state.test {
            val initialState = awaitItem()
            assertEquals(ListingState.Success::class, initialState::class)
        }

        // Filter the ads with the specific query
        viewModel.onAction(ListingAction.Filter("SoFá"))
        delay(100)
        // Assert the updated state with the filtered ads
        viewModel.state.test {
            val expectedAds = listOf(
                Ad("Image Url 1", "SOFÁ", "1", "Time", "Location1"),
                Ad("Image Url 2", "soFA", "2", "Time", "Location2"),
                Ad("Image Url 3", "2 sofás!", "3", "Time", "Location3")
            )
            assertEquals(ListingState.Success(expectedAds, "SoFá"), awaitItem())
        }
    }
}

