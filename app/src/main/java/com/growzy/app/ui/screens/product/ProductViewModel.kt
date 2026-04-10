package com.growzy.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.data.repository.Resource
import com.growzy.app.domain.repository.FundRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: FundRepository,
    private val schemeCode: Int
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())
    val state: StateFlow<ProductUiState> = _state

    init {
        fetchFundDetails()
    }

    private fun fetchFundDetails() {
        viewModelScope.launch {

            _state.value = ProductUiState(isLoading = true)

            val result = repository.getFundDetails(schemeCode)

            if (result is Resource.Success) {
                _state.value = ProductUiState(
                    data = result.data,
                    isLoading = false
                )
            } else {
                _state.value = ProductUiState(
                    isLoading = false,
                    error = "Failed"
                )
            }
        }
    }

    fun toggleWatchlist() {
        val current = _state.value
        _state.value = current.copy(
            isInWatchlist = !current.isInWatchlist
        )
    }
}