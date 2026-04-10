package com.growzy.app.ui.screens.product

import com.growzy.app.data.remote.dto.FundDetailsDto

data class ProductUiState(
    val data: FundDetailsDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)