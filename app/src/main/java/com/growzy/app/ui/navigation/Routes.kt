package com.growzy.app.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Explore

@Serializable
object Watchlist

@Serializable
data class Product(val schemeCode: Int)

@Serializable
data class ViewAll(val category: String)

@Serializable
object Search

@Serializable
data class FolderDetails(val folderId: Int)

@Serializable
object Settings