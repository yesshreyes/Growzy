package com.growzy.app.ui.screens.folderdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@Composable
fun FolderDetailsScreen(
    folderId: Int
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: FolderDetailsViewModel = viewModel(
        factory = FolderDetailsViewModelFactory(
            app.container.watchlistRepository,
            folderId
        )
    )

    val state by viewModel.state.collectAsState()

    FolderDetailsContent(
        state = state,
        onRemoveClick = { schemeCode ->
            viewModel.removeFund(schemeCode)
        }
    )
}