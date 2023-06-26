package com.olxbr.android.challenge.listing.domain

import com.olxbr.android.challenge.listing.domain.model.Ad

interface ListingRepository{

    suspend fun getAds(): List<Ad>

}