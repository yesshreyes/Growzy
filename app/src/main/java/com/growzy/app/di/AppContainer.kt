package com.growzy.app.di

import android.content.Context
import androidx.room.Room
import com.growzy.app.data.local.AppDatabase
import com.growzy.app.data.remote.api.MfApiService
import com.growzy.app.data.remote.api.RetrofitClient
import com.growzy.app.data.repository.FundRepositoryImpl
import com.growzy.app.domain.repository.FundRepository

class AppContainer(context: Context) {

    private val apiService: MfApiService = RetrofitClient.api

    val fundRepository: FundRepository by lazy {
        FundRepositoryImpl(apiService, exploreDao)
    }

    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "growzy_db"
    ).build()

    private val exploreDao = database.exploreDao()
}