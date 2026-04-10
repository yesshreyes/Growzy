package com.growzy.app.ui.screens.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growzy.app.domain.repository.FundRepository

class ViewAllViewModelFactory(
    private val repository: FundRepository,
    private val category: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewAllViewModel(repository, category) as T
    }
}