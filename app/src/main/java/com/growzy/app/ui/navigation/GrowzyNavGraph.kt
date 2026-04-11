package com.growzy.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.growzy.app.ui.screens.explore.ExploreScreen
import com.growzy.app.ui.screens.folderdetails.FolderDetailsScreen
import com.growzy.app.ui.screens.watchlist.WatchlistScreen
import com.growzy.app.ui.screens.product.ProductScreen
import com.growzy.app.ui.screens.search.SearchScreen
import com.growzy.app.ui.screens.settings.SettingsScreen
import com.growzy.app.ui.screens.view.ViewAllScreen

@Composable
fun GrowzyNavGraph() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            val isTopLevel = currentDestination?.hasRoute<Explore>() == true || 
                             currentDestination?.hasRoute<Watchlist>() == true ||
                             currentDestination?.hasRoute<Settings>() == true
            if (isTopLevel) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute<Explore>() == true,
                        onClick = {
                            navController.navigate(Explore) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Explore") },
                        label = { Text("Explore") }
                    )
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute<Watchlist>() == true,
                        onClick = {
                            navController.navigate(Watchlist) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Watchlist") },
                        label = { Text("Watchlist") }
                    )
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute<Settings>() == true,
                        onClick = {
                            navController.navigate(Settings) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = { Text("Settings") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Explore,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Explore> {
                ExploreScreen(
                    onFundClick = { schemeCode ->
                        navController.navigate(Product(schemeCode))
                    },
                    onViewAllClick = { category ->
                        navController.navigate(ViewAll(category))
                    },
                    onSearchClick = {
                        navController.navigate(Search)
                    }
                )
            }

            composable<Watchlist> {
                WatchlistScreen(
                    onFolderClick = { folderId ->
                        navController.navigate(FolderDetails(folderId))
                    },
                    onExploreClick = {
                        navController.navigate(Explore)
                    }
                )
            }

            composable<Settings> {
                SettingsScreen()
            }

            composable<FolderDetails> { backStackEntry ->

                val args = backStackEntry.toRoute<FolderDetails>()

                FolderDetailsScreen(folderId = args.folderId)
            }

            composable<Product> { backStackEntry ->

                val args = backStackEntry.toRoute<Product>()

                ProductScreen(
                    schemeCode = args.schemeCode
                )
            }

            composable<ViewAll> { backStackEntry ->

                val args = backStackEntry.toRoute<ViewAll>()

                ViewAllScreen(
                    category = args.category,
                    onFundClick = { schemeCode ->
                        navController.navigate(Product(schemeCode))
                    }
                )
            }

            composable<Search> {
                SearchScreen(
                    onFundClick = { schemeCode ->
                        navController.navigate(Product(schemeCode))
                    }
                )
            }
        }
    }
}