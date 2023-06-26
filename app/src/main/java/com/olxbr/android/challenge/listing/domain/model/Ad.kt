package com.olxbr.android.challenge.listing.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ad")
data class Ad(
    @PrimaryKey
    val subject: String,

    val thumbnail: String,
    val price: String?,
    val time: String?,
    val location: String?
)
