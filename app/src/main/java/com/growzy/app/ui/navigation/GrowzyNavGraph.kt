package com.growzy.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.growzy.app.ui.screens.explore.ExploreScreen
import com.growzy.app.ui.screens.watchlist.WatchlistScreen
import com.growzy.app.ui.screens.product.ProductScreen

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
                }
            )
        }

        // 🔹 Watchlist Screen
        composable<Watchlist> {
            WatchlistScreen()
        }

        // 🔹 Product Screen
        composable<Product> { backStackEntry ->

            val args = backStackEntry.toRoute<Product>()

            ProductScreen(
                schemeCode = args.schemeCode
            )
        }
    }
}