package com.growzy.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_funds")
data class FundEntity(
    @PrimaryKey
    val schemeCode: Int,
    val schemeName: String,
    val folderId: Int
)