package com.growzy.app.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Explore

@Serializable
object Watchlist

@Serializable
data class Product(val schemeCode: Int)