package com.growzy.app.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
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

    val isTopLevel = currentDestination?.hasRoute<Explore>() == true ||
            currentDestination?.hasRoute<Watchlist>() == true ||
            currentDestination?.hasRoute<Settings>() == true

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isTopLevel,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                GlassBottomNav(
                    currentDestination = currentDestination,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
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
                ProductScreen(schemeCode = args.schemeCode)
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

@Composable
private fun GlassBottomNav(
    currentDestination: NavDestination?,
    onNavigate: (Any) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val items = listOf(
                    Triple(Explore, "Explore", Icons.Filled.Explore to Icons.Outlined.Explore),
                    Triple(Watchlist, "Watchlist", Icons.Filled.Favorite to Icons.Outlined.FavoriteBorder),
                    Triple(Settings, "Settings", Icons.Filled.Settings to Icons.Outlined.Settings)
                )

                items.forEach { (route, label, icons) ->
                    val selected = currentDestination?.hasRoute(route::class) == true
                    val (filledIcon, outlinedIcon) = icons

                    NavigationItem(
                        selected = selected,
                        onClick = { onNavigate(route) },
                        icon = if (selected) filledIcon else outlinedIcon,
                        label = label
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String
) {
    val containerColor by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        else
            Color.Transparent,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "nav_bg"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "nav_icon"
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            if (selected) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically { it / 2 },
                    exit = fadeOut()
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = iconColor
                    )
                }
            }
        }
    }
}