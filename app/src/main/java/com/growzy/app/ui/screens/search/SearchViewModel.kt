package com.growzy.app.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.data.repository.Resource
import com.growzy.app.domain.repository.FundRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: FundRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    private var searchJob: Job? = null

    fun onQueryChange(query: String) {
        _state.value = _state.value.copy(query = query)

        // cancel previous job
        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            delay(300)   // ⏱ debounce delay

            searchFunds(query)
        }
    }

    private suspend fun searchFunds(query: String) {

        if (query.isBlank()) {
            _state.value = _state.value.copy(results = emptyList())
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)

        when (val result = repository.searchFunds(query)) {

            is Resource.Success -> {
                _state.value = _state.value.copy(
                    results = result.data,
                    isLoading = false
                )
            }

            is Resource.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message
                )
            }

            else -> {}
        }
    }
}