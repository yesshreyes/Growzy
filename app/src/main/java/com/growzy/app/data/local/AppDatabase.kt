package com.growzy.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExploreFundEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exploreDao(): ExploreDao
}