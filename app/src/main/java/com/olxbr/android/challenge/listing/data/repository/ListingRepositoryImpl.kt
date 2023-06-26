package com.olxbr.android.challenge.listing.data.repository

import com.olxbr.android.challenge.listing.data.local.AdDao
import com.olxbr.android.challenge.listing.data.remote.AdsApi
import com.olxbr.android.challenge.listing.domain.ListingRepository
import com.olxbr.android.challenge.listing.domain.model.Ad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListingRepositoryImpl(
    private val adDao: AdDao,
    private val adsApi: AdsApi
) : ListingRepository {

    override suspend fun getAds(): List<Ad> {

        val localAds = adDao.getLocalAds()

        if (localAds.isNotEmpty()) {
            return localAds
        }

        return withContext(Dispatchers.IO) {
            val remoteAds = adsApi.getAds()

            val newAds = mutableListOf<Ad>()
            for (remoteAd in remoteAds) {
                if (!localAds.contains(remoteAd)) {
                    newAds.add(remoteAd)
                }
            }

            if (newAds.isNotEmpty()) {
                adDao.insertAds(newAds)
            }
            adDao.getLocalAds()
        }
    }
}


