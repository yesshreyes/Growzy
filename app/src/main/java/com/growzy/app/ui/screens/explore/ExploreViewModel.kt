package com.growzy.app.ui.screens.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.domain.repository.FundRepository
import com.growzy.app.data.repository.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val repository: FundRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ExploreUiState())
    val state: StateFlow<ExploreUiState> = _state

    init {
        fetchExploreData()
    }

    private fun fetchExploreData() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            val indexFunds = repository.searchFunds("index")
            val bluechipFunds = repository.searchFunds("bluechip")
            val taxFunds = repository.searchFunds("tax")
            val largeCapFunds = repository.searchFunds("large cap")

            if (indexFunds is Resource.Success &&
                bluechipFunds is Resource.Success &&
                taxFunds is Resource.Success &&
                largeCapFunds is Resource.Success
            ) {
                _state.value = ExploreUiState(
                    indexFunds = indexFunds.data.take(4),
                    bluechipFunds = bluechipFunds.data.take(4),
                    taxFunds = taxFunds.data.take(4),
                    largeCapFunds = largeCapFunds.data.take(4),
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Failed to load funds"
                )
            }
        }
    }
}