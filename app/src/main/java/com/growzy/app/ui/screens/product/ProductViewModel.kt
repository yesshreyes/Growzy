package com.growzy.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.data.local.entity.WatchlistFolder
import com.growzy.app.data.repository.Resource
import com.growzy.app.domain.repository.FundRepository
import com.growzy.app.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class  ProductViewModel(
    private val fundRepository: FundRepository,
    private val watchlistRepository: WatchlistRepository,
    private val schemeCode: Int
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())
    val state: StateFlow<ProductUiState> = _state

    private val _folders = MutableStateFlow<List<WatchlistFolder>>(emptyList())
    val folders: StateFlow<List<WatchlistFolder>> = _folders

    init {
        fetchFundDetails()
    }

    private fun fetchFundDetails() {
        viewModelScope.launch {

            _state.value = ProductUiState(isLoading = true)

            val result = fundRepository.getFundDetails(schemeCode)

            when (result) {
                is Resource.Success -> {
                    val inWatchlist = watchlistRepository.checkInWatchlist(schemeCode)
                    _state.value = ProductUiState(
                        data = result.data,
                        isLoading = false,
                        isInWatchlist = inWatchlist
                    )
                }
                is Resource.Error -> {
                    _state.value = ProductUiState(
                        isLoading = false,
                        error = result.message
                    )
                }
                else -> {}
            }
        }
    }

    fun loadFolders() {
        viewModelScope.launch {
            _folders.value = watchlistRepository.getFolders()
        }
    }

    fun addToWatchlist(
        selectedFolderIds: List<Int>,
        newFolderName: String,
        schemeName: String
    ) {
        viewModelScope.launch {

            var folderIds = selectedFolderIds

            if (newFolderName.isNotBlank()) {
                watchlistRepository.createFolder(newFolderName)
                val updatedFolders = watchlistRepository.getFolders()
                val newFolder = updatedFolders.last()
                folderIds = folderIds + newFolder.id
            }

            folderIds.forEach { folderId ->
                watchlistRepository.addFundToFolder(
                    schemeCode = schemeCode,
                    schemeName = schemeName,
                    folderId = folderId
                )
            }

            _state.value = _state.value.copy(isInWatchlist = true)

            loadFolders()
        }
    }
}