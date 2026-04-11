package com.growzy.app.ui.screens.folderdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growzy.app.domain.repository.WatchlistRepository

class FolderDetailsViewModelFactory(
    private val repository: WatchlistRepository,
    private val folderId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FolderDetailsViewModel(repository, folderId) as T
    }
}