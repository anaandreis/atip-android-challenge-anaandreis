package com.olxbr.android.challenge.listing.data.remote

import com.olxbr.android.challenge.listing.domain.model.Ad
import retrofit2.http.GET

interface AdsApi {
    @GET("ads.json")
    suspend fun getAds(): List<Ad>
}