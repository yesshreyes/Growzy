package com.growzy.app.ui.screens.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.growzy.app.data.remote.dto.FundSearchDto

@Composable
fun CategorySection(
    title: String,
    funds: List<FundSearchDto>,
    onFundClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {

    Column(
        modifier = Modifier.padding(bottom = 24.dp)
    ) {

        CategoryHeader(
            title = title,
            onViewAllClick = onViewAllClick
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.heightIn(max = 240.dp)
        ) {
            items(funds) { fund ->
                FundCard(fund, onFundClick)
            }
        }
    }
}

@Composable
fun CategoryHeader(
    title: String,
    onViewAllClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        TextButton(
            onClick = { onViewAllClick() }
        ) {
            Text("View All")
        }
    }
}