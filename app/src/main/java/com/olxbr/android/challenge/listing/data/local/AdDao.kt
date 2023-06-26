package com.olxbr.android.challenge.listing.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olxbr.android.challenge.listing.domain.model.Ad


@Dao
interface AdDao {

    @Query("SELECT * FROM ad")
    suspend fun getLocalAds(): List<Ad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAds(ads: List<Ad>)

}