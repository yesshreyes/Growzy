package com.growzy.app.ui.screens.folderdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FolderDetailsViewModel(
    private val repository: WatchlistRepository,
    private val folderId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(FolderDetailsUiState())
    val state: StateFlow<FolderDetailsUiState> = _state

    init {
        loadFunds()
    }

    fun loadFunds() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val funds = repository.getFunds(folderId)

                _state.value = FolderDetailsUiState(
                    funds = funds,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = FolderDetailsUiState(
                    isLoading = false,
                    error = "Failed to load funds"
                )
            }
        }
    }

    fun removeFund(schemeCode: Int) {
        viewModelScope.launch {
            repository.removeFund(schemeCode, folderId)
            loadFunds() // refresh
        }
    }
}