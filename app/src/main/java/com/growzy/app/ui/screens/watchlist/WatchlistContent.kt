package com.growzy.app.ui.screens.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.growzy.app.data.local.entity.WatchlistFolder
import com.growzy.app.ui.screens.watchlist.components.EmptyWatchlistView

@Composable
fun WatchlistContent(
    state: WatchlistUiState,
    onFolderClick: (Int) -> Unit,
    onExploreClick: () -> Unit
) {

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error)
            }
        }

        state.folders.isEmpty() -> {
            EmptyWatchlistView(onExploreClick)
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.folders) { folder ->
                    FolderItem(folder, onFolderClick)
                }
            }
        }
    }
}

@Composable
fun FolderItem(
    folder: WatchlistFolder,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(folder.id) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = folder.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

