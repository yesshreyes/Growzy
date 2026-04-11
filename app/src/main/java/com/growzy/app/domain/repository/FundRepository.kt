package com.growzy.app.domain.repository

import com.growzy.app.data.remote.dto.FundDetailsDto
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.data.repository.Resource

interface FundRepository {

    suspend fun searchFunds(query: String): Resource<List<FundSearchDto>>

    suspend fun getFundDetails(schemeCode: Int): Resource<FundDetailsDto>

    suspend fun clearCache()
}