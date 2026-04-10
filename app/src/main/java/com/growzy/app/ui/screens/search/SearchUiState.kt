package com.growzy.app.ui.screens.search

import com.growzy.app.data.remote.dto.FundSearchDto

data class SearchUiState(
    val query: String = "",
    val results: List<FundSearchDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)