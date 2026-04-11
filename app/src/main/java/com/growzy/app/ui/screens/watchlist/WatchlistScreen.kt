package com.growzy.app.ui.screens.watchlist

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@Composable
fun WatchlistScreen(
    onFolderClick: (Int) -> Unit,
    onExploreClick: () -> Unit,
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: WatchlistViewModel = viewModel(
        factory = WatchlistViewModelFactory(app.container.watchlistRepository)
    )

    val state by viewModel.state.collectAsState()

    WatchlistContent(
        state = state,
        onFolderClick = onFolderClick,
        onExploreClick = onExploreClick,
        onDeleteFolder = { folderId ->
            viewModel.deleteFolder(folderId)
        }
    )
}