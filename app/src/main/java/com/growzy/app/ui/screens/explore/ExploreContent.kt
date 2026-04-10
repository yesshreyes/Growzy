package com.growzy.app.ui.screens.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.ui.screens.explore.components.CategorySection

@Composable
fun ExploreContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit
) {

    when {
        state.isLoading -> {
            LoadingView()
        }

        state.error != null -> {
            ErrorView(message = state.error)
        }

        state.indexFunds.isEmpty() &&
                state.bluechipFunds.isEmpty() &&
                state.taxFunds.isEmpty() &&
                state.largeCapFunds.isEmpty() -> {
            EmptyView()
        }

        else -> {
            ExploreSuccessContent(state, onFundClick)
        }
    }
}

@Composable
fun ExploreSuccessContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        CategorySection("Index Funds", state.indexFunds, onFundClick)
        CategorySection("Bluechip Funds", state.bluechipFunds, onFundClick)
        CategorySection("Tax Saver (ELSS)", state.taxFunds, onFundClick)
        CategorySection("Large Cap Funds", state.largeCapFunds, onFundClick)

        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun LoadingView() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
fun EmptyView() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No funds available")
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreContentPreview() {

    val dummyFunds = List(4) {
        com.growzy.app.data.remote.dto.FundSearchDto(
            schemeCode = it,
            schemeName = "Sample Fund $it"
        )
    }

    ExploreContent(
        state = ExploreUiState(
            indexFunds = dummyFunds,
            bluechipFunds = dummyFunds,
            taxFunds = dummyFunds,
            largeCapFunds = dummyFunds
        ),
        onFundClick = {}
    )
}