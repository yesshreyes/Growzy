package com.growzy.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growzy.app.domain.repository.FundRepository

class ProductViewModelFactory(
    private val repository: FundRepository,
    private val schemeCode: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repository, schemeCode) as T
    }
}