package com.growzy.app.ui.screens.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.growzy.app.data.local.entity.WatchlistFolder
import com.growzy.app.ui.screens.watchlist.components.EmptyWatchlistView
import com.growzy.app.ui.theme.GrowzyTheme

@Composable
fun WatchlistContent(
    state: WatchlistUiState,
    onFolderClick: (Int) -> Unit,
    onExploreClick: () -> Unit,
    onDeleteFolder: (Int) -> Unit
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

                items(
                    items = state.folders,
                    key = { it.id }
                ) { folder ->
                    FolderItem(
                        folder = folder,
                        onClick = onFolderClick,
                        onDelete = onDeleteFolder
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderItem(
    folder: WatchlistFolder,
    onClick: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.EndToStart -> {
                    onDelete(folder.id)
                    true
                }
                else -> false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.errorContainer
                    else -> Color.Transparent
                }
            )
            val iconColor = MaterialTheme.colorScheme.onErrorContainer

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Folder",
                        tint = iconColor
                    )
                }
            }
        },
        enableDismissFromStartToEnd = false
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
}

@Preview(showBackground = true)
@Composable
fun WatchlistContentPreview() {
    GrowzyTheme {
        WatchlistContent(
            state = WatchlistUiState(
                folders = listOf(
                    WatchlistFolder(id = 1, name = "My Stocks"),
                    WatchlistFolder(id = 2, name = "Tech Funds")
                )
            ),
            onFolderClick = {},
            onExploreClick = {},
            onDeleteFolder = {}
        )
    }
}

