package com.growzy.app.ui.screens.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.ui.screens.explore.components.CategorySection

@Composable
fun ExploreContent(
    state: ExploreUiState,
    onFundClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CategorySection(
            title = "Index Funds",
            funds = state.indexFunds,
            onFundClick = onFundClick
        )

        CategorySection(
            title = "Bluechip Funds",
            funds = state.bluechipFunds,
            onFundClick = onFundClick
        )

        CategorySection(
            title = "Tax Saver (ELSS)",
            funds = state.taxFunds,
            onFundClick = onFundClick
        )

        CategorySection(
            title = "Large Cap Funds",
            funds = state.largeCapFunds,
            onFundClick = onFundClick
        )
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