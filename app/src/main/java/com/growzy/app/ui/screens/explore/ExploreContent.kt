package com.growzy.app.ui.screens.explore

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExploreContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit
) {
    Text(text = "Index Funds: ${state.indexFunds.size}")
}

@Preview(showBackground = true)
@Composable
fun ExploreContentPreview() {
    ExploreContent(
        state = ExploreUiState(
            indexFunds = List(4) {
                com.growzy.app.data.remote.dto.FundSearchDto(
                    schemeCode = it,
                    schemeName = "Sample Fund $it"
                )
            }
        ),
        onFundClick = {}
    )
}