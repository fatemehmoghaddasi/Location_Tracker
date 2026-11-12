package com.example.locationtracker.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Room
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.locationtracker.navigation.HistoryScreenRoute
import com.example.locationtracker.navigation.HomeScreenRoute
import com.example.locationtracker.navigation.MapScreenRoute

data class BottomNavItem(
    val title : String,
    val icon : ImageVector,
    val route : Any
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = HomeScreenRoute
    ),
    BottomNavItem(
        title = "Map",
        icon = Icons.Default.Room,
        route = MapScreenRoute
    ),
    BottomNavItem(
        title = "History",
        icon = Icons.Default.History,
        route = HistoryScreenRoute
    )
)
