package com.growzy.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.growzy.app.ui.screens.explore.ExploreScreen
import com.growzy.app.ui.screens.folderdetails.FolderDetailsScreen
import com.growzy.app.ui.screens.watchlist.WatchlistScreen
import com.growzy.app.ui.screens.product.ProductScreen
import com.growzy.app.ui.screens.search.SearchScreen
import com.growzy.app.ui.screens.view.ViewAllScreen

@Composable
fun GrowzyNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Explore
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