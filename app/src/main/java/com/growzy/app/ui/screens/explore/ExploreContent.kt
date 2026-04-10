package com.growzy.app.ui.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.ui.screens.explore.components.CategorySection

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit,
    onViewAllClick: (String) -> Unit,
    onSearchClick: () -> Unit = {}
) {

    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("Explore") },
                actions = {
                    androidx.compose.material3.IconButton(onClick = onSearchClick) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Search,
                            contentDescription = "Search Funds"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
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
                    ExploreSuccessContent(
                        state = state,
                        onFundClick = onFundClick,
                        onViewAllClick = onViewAllClick
                    )
                }
            }
        }
    }
}

@Composable
fun ExploreSuccessContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit,
    onViewAllClick: (String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            CategorySection(
                title = "Index Funds",
                funds = state.indexFunds,
                onFundClick = onFundClick,
                onViewAllClick = { onViewAllClick("index") }
            )
        }

        item {
            CategorySection(
                title = "Bluechip Funds",
                funds = state.bluechipFunds,
                onFundClick = onFundClick,
                onViewAllClick = { onViewAllClick("bluechip") }
            )
        }

        item {
            CategorySection(
                title = "Tax Saver (ELSS)",
                funds = state.taxFunds,
                onFundClick = onFundClick,
                onViewAllClick = { onViewAllClick("tax") }
            )
        }

        item {
            CategorySection(
                title = "Large Cap Funds",
                funds = state.largeCapFunds,
                onFundClick = onFundClick,
                onViewAllClick = { onViewAllClick("large cap") }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
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
        onFundClick = {},
        onViewAllClick = {}
    )
}