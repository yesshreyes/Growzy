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
            schemeCode
        )
    )

    val state by viewModel.state.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }

    ProductContent(
        state = state,
        onToggleWatchlist = {
            showBottomSheet = true
        }
    )

    if (showBottomSheet) {
        AddToWatchlistBottomSheet(
            folders = emptyList(),
            onDismiss = { showBottomSheet = false },
            onAddClick = { _, _ -> }
        )
    }
}