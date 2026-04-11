package com.growzy.app.data.repository

import com.growzy.app.data.local.dao.WatchlistDao
import com.growzy.app.data.local.entity.FundEntity
import com.growzy.app.data.local.entity.WatchlistFolder
import com.growzy.app.domain.repository.WatchlistRepository

class WatchlistRepositoryImpl(
    private val dao: WatchlistDao
) : WatchlistRepository {

    override suspend fun createFolder(name: String) {
        dao.insertFolder(
            WatchlistFolder(name = name)
        )
    }

    override suspend fun getFolders(): List<WatchlistFolder> {
        return dao.getAllFolders()
    }

    override suspend fun addFundToFolder(
        schemeCode: Int,
        schemeName: String,
        folderId: Int
    ) {
        dao.insertFund(
            FundEntity(
                schemeCode = schemeCode,
                schemeName = schemeName,
                folderId = folderId
            )
        )
    }

    override suspend fun getFunds(folderId: Int): List<FundEntity> {
        return dao.getFundsByFolder(folderId)
    }

    override suspend fun removeFund(
        schemeCode: Int,
        folderId: Int
    ) {
        dao.removeFund(schemeCode, folderId)
    }

    override suspend fun deleteFolder(folderId: Int) {
        dao.deleteFundsByFolder(folderId)
        dao.deleteFolder(folderId)
    }

    override suspend fun checkInWatchlist(schemeCode: Int): Boolean {
        return dao.checkInWatchlist(schemeCode)
    }
}