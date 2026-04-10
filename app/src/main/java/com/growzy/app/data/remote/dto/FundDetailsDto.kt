package com.growzy.app.data.remote.dto

data class FundDetailsDto(
    val meta: MetaDto,
    val data: List<NavDataDto>
)

data class MetaDto(
    val fund_house: String,
    val scheme_type: String,
    val scheme_name: String
)

data class NavDataDto(
    val date: String,
    val nav: String
)