package com.growzy.app.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.data.remote.dto.FundSearchDto
import com.growzy.app.ui.screens.explore.components.FundCard
import com.growzy.app.ui.theme.GrowzyTheme

@Composable
fun SearchContent(
    state: SearchUiState,
    onQueryChange: (String) -> Unit,
    onFundClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = state.query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search mutual funds...") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(text = state.error)
            }

            state.results.isEmpty() && state.query.isNotBlank() -> {
                Text(text = "No results found")
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.results) { fund ->
                        FundCard(
                            fund = fund,
                            onClick = onFundClick
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchContentPreview() {
    GrowzyTheme {
        SearchContent(
            state = SearchUiState(
                query = "Tata",
                results = listOf(
                    FundSearchDto(1, "Tata Digital India Fund")
                )
            ),
            onQueryChange = {},
            onFundClick = {}
        )
    }
}