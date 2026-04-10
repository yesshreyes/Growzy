package com.growzy.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "explore_funds")
data class ExploreFundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val schemeCode: Int,
    val schemeName: String,
    val category: String
)