package com.growzy.app.ui.screens.product

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

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

    ProductContent(
        state = state,
        onToggleWatchlist = { viewModel.toggleWatchlist() }
    )
}