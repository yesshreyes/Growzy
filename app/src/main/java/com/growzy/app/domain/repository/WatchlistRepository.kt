package com.growzy.app.domain.repository

import com.growzy.app.data.local.entity.FundEntity
import com.growzy.app.data.local.entity.WatchlistFolder

interface WatchlistRepository {

    suspend fun createFolder(name: String)

    suspend fun getFolders(): List<WatchlistFolder>

    suspend fun addFundToFolder(
        schemeCode: Int,
        schemeName: String,
        folderId: Int
    )

    suspend fun getFunds(folderId: Int): List<FundEntity>

    suspend fun removeFund(
        schemeCode: Int,
        folderId: Int
    )
}