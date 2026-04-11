package com.growzy.app.ui.screens.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.ui.screens.explore.components.FundCard
import com.growzy.app.ui.theme.GrowzyTheme

@Composable
fun ViewAllContent(
    state: ViewAllUiState,
    onLoadMore: () -> Unit,
    onFundClick: (Int) -> Unit
) {

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastIndex ->

            if (lastIndex == state.visibleFunds.lastIndex) {
                onLoadMore()
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(state.visibleFunds) { fund ->
            FundCard(fund = fund, onClick = onFundClick)
        }

        if (state.isLoadingMore) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewAllContentPreview() {
    GrowzyTheme {
        ViewAllContent(
            state = ViewAllUiState(
                visibleFunds = listOf(
                    FundSearchDto(1, "Sample Fund A"),
                    FundSearchDto(2, "Sample Fund B")
                )
            ),
            onLoadMore = {},
            onFundClick = {}
        )
    }
}