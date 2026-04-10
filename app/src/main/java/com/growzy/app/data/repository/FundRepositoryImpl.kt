package com.growzy.app.data.repository

import com.growzy.app.data.remote.api.MfApiService
import com.growzy.app.data.remote.dto.FundDetailsDto
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.domain.repository.FundRepository

class FundRepositoryImpl(
    private val api: MfApiService
) : FundRepository {

    override suspend fun searchFunds(query: String): Resource<List<FundSearchDto>> {
        return try {
            val response = api.searchFunds(query)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getFundDetails(schemeCode: Int): Resource<FundDetailsDto> {
        return try {
            val response = api.getFundDetails(schemeCode)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch fund details")
        }
    }
}