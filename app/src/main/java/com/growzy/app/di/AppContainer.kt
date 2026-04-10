package com.growzy.app.di

import com.growzy.app.data.remote.api.MfApiService
import com.growzy.app.data.remote.api.RetrofitClient
import com.growzy.app.data.repository.FundRepositoryImpl
import com.growzy.app.domain.repository.FundRepository

class AppContainer {

    private val apiService: MfApiService = RetrofitClient.api

    val fundRepository: FundRepository by lazy {
        FundRepositoryImpl(apiService)
    }
}