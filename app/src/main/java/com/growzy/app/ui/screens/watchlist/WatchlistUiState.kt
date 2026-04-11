package com.growzy.app.ui.screens.watchlist

import com.growzy.app.data.local.entity.WatchlistFolder

data class WatchlistUiState(
    val folders: List<WatchlistFolder> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)