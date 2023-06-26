package com.olxbr.android.challenge.listing.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olxbr.android.challenge.listing.domain.model.Ad


@Database(entities = [Ad::class], version = 1)
abstract class AdDatabase : RoomDatabase() {
    abstract fun adDao(): AdDao

    companion object {
        private const val DATABASE_NAME = "ad-database"

        @Volatile
        private var INSTANCE: AdDatabase? = null

        fun getInstance(context: Context): AdDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AdDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
