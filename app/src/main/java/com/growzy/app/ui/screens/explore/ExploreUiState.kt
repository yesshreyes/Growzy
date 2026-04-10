package com.growzy.app.ui.screens.explore

import com.growzy.app.data.remote.dto.FundSearchDto

data class ExploreUiState(
    val indexFunds: List<FundSearchDto> = emptyList(),
    val bluechipFunds: List<FundSearchDto> = emptyList(),
    val taxFunds: List<FundSearchDto> = emptyList(),
    val largeCapFunds: List<FundSearchDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)