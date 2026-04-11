package com.growzy.app.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: WatchlistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WatchlistUiState())
    val state: StateFlow<WatchlistUiState> = _state

    init {
        loadFolders()
    }

    fun loadFolders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val folders = repository.getFolders()

                _state.value = WatchlistUiState(
                    folders = folders,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = WatchlistUiState(
                    isLoading = false,
                    error = "Failed to load folders"
                )
            }
        }
    }

    fun deleteFolder(folderId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteFolder(folderId)
                loadFolders()
            } catch (e: Exception) {
                // Ignore or handle
            }
        }
    }
}