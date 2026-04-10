package com.growzy.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.growzy.app.data.local.dao.ExploreDao
import com.growzy.app.data.local.dao.WatchlistDao
import com.growzy.app.data.local.entity.ExploreFundEntity
import com.growzy.app.data.local.entity.FundEntity
import com.growzy.app.data.local.entity.WatchlistFolder

@Database(
    entities = [
        ExploreFundEntity::class,
        WatchlistFolder::class,
        FundEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exploreDao(): ExploreDao
    abstract fun watchlistDao(): WatchlistDao
}