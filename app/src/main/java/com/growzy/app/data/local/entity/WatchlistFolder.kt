package com.growzy.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_folders")
data class WatchlistFolder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)