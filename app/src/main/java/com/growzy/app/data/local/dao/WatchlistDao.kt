package com.growzy.app.data.local.dao

import androidx.room.*
import com.growzy.app.data.local.entity.FundEntity
import com.growzy.app.data.local.entity.WatchlistFolder

@Dao
interface WatchlistDao {

    @Insert
    suspend fun insertFolder(folder: WatchlistFolder)

    @Query("SELECT * FROM watchlist_folders")
    suspend fun getAllFolders(): List<WatchlistFolder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFund(fund: FundEntity)

    @Query("SELECT * FROM watchlist_funds WHERE folderId = :folderId")
    suspend fun getFundsByFolder(folderId: Int): List<FundEntity>

    @Query("DELETE FROM watchlist_funds WHERE schemeCode = :schemeCode AND folderId = :folderId")
    suspend fun removeFund(schemeCode: Int, folderId: Int)

    @Query("DELETE FROM watchlist_folders WHERE id = :folderId")
    suspend fun deleteFolder(folderId: Int)

    @Query("DELETE FROM watchlist_funds WHERE folderId = :folderId")
    suspend fun deleteFundsByFolder(folderId: Int)
}