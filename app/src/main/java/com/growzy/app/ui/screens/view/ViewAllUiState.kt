package com.growzy.app.ui.screens.view

import com.growzy.app.data.remote.dto.FundSearchDto

data class ViewAllUiState(
    val allFunds: List<FundSearchDto> = emptyList(),
    val visibleFunds: List<FundSearchDto> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)