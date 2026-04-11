package com.growzy.app.ui.screens.folderdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growzy.app.data.local.entity.FundEntity
import com.growzy.app.ui.theme.GrowzyTheme

@Composable
fun FolderDetailsContent(
    state: FolderDetailsUiState,
    onRemoveClick: (Int) -> Unit
) {

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }

        state.error != null -> {
            Text(text = state.error)
        }

        state.funds.isEmpty() -> {
            Text("No funds in this folder")
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.funds) { fund ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = fund.schemeName,
                                modifier = Modifier.weight(1f)
                            )

                            TextButton(
                                onClick = {
                                    onRemoveClick(fund.schemeCode)
                                }
                            ) {
                                Text("Remove")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FolderDetailsContentPreview() {
    GrowzyTheme {
        FolderDetailsContent(
            state = FolderDetailsUiState(
                funds = listOf(
                    FundEntity(
                        schemeCode = 1,
                        schemeName = "Sample Fund 1",
                        folderId = 1
                    )
                )
            ),
            onRemoveClick = {}
        )
    }
}