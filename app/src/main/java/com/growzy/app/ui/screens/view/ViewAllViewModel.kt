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

    private val pageSize = 10
    private var currentIndex = 0
    private var isLoadingMore = false

    init {
        loadInitial()
    }

    private fun loadInitial() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            val result = repository.searchFunds(category)

            if (result is Resource.Success) {

                val all = result.data

                val firstPage = all.take(pageSize)

                currentIndex = firstPage.size

                _state.value = ViewAllUiState(
                    allFunds = all,
                    visibleFunds = firstPage,
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

    fun loadMore() {
        if (isLoadingMore) return

        val currentState = _state.value

        if (currentIndex >= currentState.allFunds.size) return

        viewModelScope.launch {

            isLoadingMore = true

            _state.value = currentState.copy(isLoadingMore = true)

            val nextIndex = (currentIndex + pageSize)
                .coerceAtMost(currentState.allFunds.size)

            val newItems = currentState.allFunds.subList(currentIndex, nextIndex)

            currentIndex = nextIndex

            _state.value = currentState.copy(
                visibleFunds = currentState.visibleFunds + newItems,
                isLoadingMore = false
            )

            isLoadingMore = false
        }
    }
}