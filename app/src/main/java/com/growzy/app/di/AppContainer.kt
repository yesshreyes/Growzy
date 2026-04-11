package com.growzy.app.di

import android.content.Context
import androidx.room.Room
import com.growzy.app.data.local.AppDatabase
import com.growzy.app.data.remote.api.MfApiService
import com.growzy.app.data.remote.api.RetrofitClient
import com.growzy.app.data.repository.FundRepositoryImpl
import com.growzy.app.data.repository.WatchlistRepositoryImpl
import com.growzy.app.domain.repository.FundRepository
import com.growzy.app.domain.repository.WatchlistRepository

class AppContainer(context: Context) {

    private val apiService: MfApiService = RetrofitClient.api

    private val database: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "growzy_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    private val exploreDao = database.exploreDao()
    val watchlistDao = database.watchlistDao()

    val fundRepository: FundRepository by lazy {
        FundRepositoryImpl(apiService, exploreDao)
    }

    val watchlistRepository: WatchlistRepository by lazy {
        WatchlistRepositoryImpl(watchlistDao)
    }
}