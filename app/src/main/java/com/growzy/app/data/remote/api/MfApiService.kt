package com.growzy.app.data.remote.api

import com.growzy.app.data.remote.dto.FundDetailsDto
import com.growzy.app.data.remote.dto.FundSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MfApiService {

    @GET("mf/search")
    suspend fun searchFunds(
        @Query("q") query: String
    ): List<FundSearchDto>

    @GET("mf/{scheme_code}")
    suspend fun getFundDetails(
        @Path("scheme_code") schemeCode: Int
    ): FundDetailsDto
}