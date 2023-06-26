package com.olxbr.android.challenge.listing.di

import android.app.Application
import androidx.room.Room
import com.olxbr.android.challenge.listing.data.local.AdDao
import com.olxbr.android.challenge.listing.data.local.AdDatabase
import com.olxbr.android.challenge.listing.data.repository.ListingRepositoryImpl
import com.olxbr.android.challenge.listing.data.remote.AdsApi
import com.olxbr.android.challenge.listing.domain.ListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAdDatabase(application: Application): AdDatabase {
        return Room.databaseBuilder(
            application,
            AdDatabase::class.java,
            "ad-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAdDao(adDatabase: AdDatabase): AdDao = adDatabase.adDao()

    @Provides
    @Singleton
    fun provideAdsApi(): AdsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://d30jh267s60dz7.cloudfront.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AdsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListingRepository(adDao: AdDao, adsApi: AdsApi): ListingRepository {
        return ListingRepositoryImpl(adDao, adsApi)
    }



    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}



