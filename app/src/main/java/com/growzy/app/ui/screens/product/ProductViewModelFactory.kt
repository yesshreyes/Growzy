package com.growzy.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growzy.app.domain.repository.FundRepository
import com.growzy.app.domain.repository.WatchlistRepository

class ProductViewModelFactory(
    private val fundRepository: FundRepository,
    private val watchlistRepository: WatchlistRepository,
    private val schemeCode: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(
            fundRepository,
            watchlistRepository,
            schemeCode
        ) as T
    }
}