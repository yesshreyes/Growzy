package com.growzy.app.ui.screens.product.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.growzy.app.data.local.entity.WatchlistFolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToWatchlistBottomSheet(
    folders: List<WatchlistFolder>,
    onDismiss: () -> Unit,
    onAddClick: (List<Int>, String) -> Unit // selected folders + new folder name
) {

    var selectedFolders by remember { mutableStateOf(setOf<Int>()) }
    var newFolderName by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Add to Watchlist",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.heightIn(max = 300.dp)
            ) {

                items(folders) { folder ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(text = folder.name)

                        Checkbox(
                            checked = selectedFolders.contains(folder.id),
                            onCheckedChange = { checked ->
                                selectedFolders =
                                    if (checked) selectedFolders + folder.id
                                    else selectedFolders - folder.id
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = newFolderName,
                onValueChange = { newFolderName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Create new folder") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onAddClick(selectedFolders.toList(), newFolderName)
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}