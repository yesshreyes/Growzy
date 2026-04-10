package com.growzy.app.ui.screens.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@Composable
fun ViewAllScreen(
    category: String,
    onFundClick: (Int) -> Unit = {}
) {

    val app = LocalContext.current.applicationContext as GrowzyApp

    val viewModel: ViewAllViewModel = viewModel(
        factory = ViewAllViewModelFactory(
            app.container.fundRepository,
            category
        )
    )

    val state by viewModel.state.collectAsState()

    ViewAllContent(
        state = state,
        onFundClick = onFundClick
    )
}