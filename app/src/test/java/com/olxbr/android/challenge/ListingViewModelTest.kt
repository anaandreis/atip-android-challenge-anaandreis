package com.olxbr.android.challenge
/*import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.olxbr.android.challenge.listing.domain.ListingRepository
import com.olxbr.android.challenge.listing.presentation.ListingAction
import com.olxbr.android.challenge.listing.presentation.ListingState
import com.olxbr.android.challenge.listing.presentation.ListingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ListingViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @Test
    fun shouldInitializeSuccessfully() = runTest {
        val repository: ListingRepository = mockk()
        coEvery { repository.getAds() } returns listOf()

        val viewModel = ListingViewModel(repository)

        viewModel.state.test {
            assertEquals(ListingState.Uninitialized, awaitItem())
            viewModel.onAction(ListingAction.Initialize)
            assertEquals(ListingState.Success(listOf()), awaitItem())
        }
    }
}
*/