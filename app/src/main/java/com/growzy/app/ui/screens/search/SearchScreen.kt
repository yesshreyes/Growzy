package com.growzy.app.ui.screens.search

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@Composable
fun SearchScreen(
    onFundClick: (Int) -> Unit
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: SearchViewModel = viewModel(
        factory = SearchViewModelFactory(app.container.fundRepository)
    )

    val state by viewModel.state.collectAsState()

    SearchContent(
        state = state,
        onQueryChange = viewModel::onQueryChange,
        onFundClick = onFundClick
    )
}