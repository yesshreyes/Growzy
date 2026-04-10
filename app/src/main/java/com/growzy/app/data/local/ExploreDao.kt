package com.growzy.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExploreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFunds(funds: List<ExploreFundEntity>)

    @Query("SELECT * FROM explore_funds WHERE category = :category")
    suspend fun getFundsByCategory(category: String): List<ExploreFundEntity>

    @Query("DELETE FROM explore_funds WHERE category = :category")
    suspend fun clearCategory(category: String)
}