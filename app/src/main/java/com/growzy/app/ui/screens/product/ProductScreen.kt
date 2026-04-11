package com.growzy.app.ui.screens.product

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp
import com.growzy.app.ui.screens.product.components.AddToWatchlistBottomSheet

@Composable
fun ProductScreen(
    schemeCode: Int
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(
            app.container.fundRepository,
            app.container.watchlistRepository,
            schemeCode
        )
    )

    val state by viewModel.state.collectAsState()
    val folders by viewModel.folders.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            viewModel.loadFolders()
        }
    }

    ProductContent(
        state = state,
        onToggleWatchlist = {
            showBottomSheet = true
        }
    )

    if (showBottomSheet) {
        AddToWatchlistBottomSheet(
            folders = folders,
            onDismiss = { showBottomSheet = false },
            onAddClick = { selectedIds, newFolder ->

                val schemeName = state.data?.meta?.scheme_name ?: ""

                viewModel.addToWatchlist(
                    selectedFolderIds = selectedIds,
                    newFolderName = newFolder,
                    schemeName = schemeName
                )

                showBottomSheet = false
            }
        )
    }
}