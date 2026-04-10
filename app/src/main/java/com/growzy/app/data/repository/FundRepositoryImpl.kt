package com.growzy.app.data.repository

import com.growzy.app.data.local.ExploreDao
import com.growzy.app.data.local.ExploreFundEntity
import com.growzy.app.data.remote.api.MfApiService
import com.growzy.app.data.remote.dto.FundDetailsDto
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.domain.repository.FundRepository

class FundRepositoryImpl(
    private val api: MfApiService,
    private val dao: ExploreDao
) : FundRepository {

    override suspend fun searchFunds(query: String): Resource<List<FundSearchDto>> {
        return try {

            val response = api.searchFunds(query)

            dao.clearCategory(query)

            dao.insertFunds(
                response.map {
                    ExploreFundEntity(
                        schemeCode = it.schemeCode,
                        schemeName = it.schemeName,
                        category = query
                    )
                }
            )

            Resource.Success(response)

        } catch (e: Exception) {

            val cached = dao.getFundsByCategory(query)

            if (cached.isNotEmpty()) {
                Resource.Success(
                    cached.map {
                        FundSearchDto(
                            schemeCode = it.schemeCode,
                            schemeName = it.schemeName
                        )
                    }
                )
            } else {
                Resource.Error("No internet & no cached data")
            }
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