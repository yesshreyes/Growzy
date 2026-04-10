package com.growzy.app.ui.screens.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.growzy.app.ui.screens.explore.components.FundCard

@Composable
fun ViewAllContent(
    state: ViewAllUiState,
    onFundClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(state.funds) { fund ->
            FundCard(fund = fund, onClick = onFundClick)
        }
    }
}