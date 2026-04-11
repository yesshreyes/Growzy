package com.growzy.app.ui.screens.folderdetails

import com.growzy.app.data.local.entity.FundEntity

data class FolderDetailsUiState(
    val funds: List<FundEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)