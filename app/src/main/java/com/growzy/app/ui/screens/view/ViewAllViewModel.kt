package com.growzy.app.ui.screens.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.data.repository.Resource
import com.growzy.app.domain.repository.FundRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewAllViewModel(
    private val repository: FundRepository,
    private val category: String
) : ViewModel() {

    private val _state = MutableStateFlow(ViewAllUiState())
    val state: StateFlow<ViewAllUiState> = _state

    init {
        loadFunds()
    }

    private fun loadFunds() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            val result = repository.searchFunds(category)

            if (result is Resource.Success) {
                _state.value = ViewAllUiState(
                    funds = result.data,
                    isLoading = false
                )
            } else {
                _state.value = ViewAllUiState(
                    isLoading = false,
                    error = "Failed to load"
                )
            }
        }
    }
}