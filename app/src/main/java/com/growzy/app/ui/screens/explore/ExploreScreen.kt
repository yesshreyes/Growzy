package com.growzy.app.ui.screens.explore

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@Composable
fun ExploreScreen(
    onFundClick: (Int) -> Unit,
    onViewAllClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: ExploreViewModel = viewModel(
        factory = ExploreViewModelFactory(app.container.fundRepository)
    )

    val state by viewModel.state.collectAsState()

    ExploreContent(
        state = state,
        onFundClick = onFundClick,
        onViewAllClick = onViewAllClick,
        onSearchClick = onSearchClick
    )
}