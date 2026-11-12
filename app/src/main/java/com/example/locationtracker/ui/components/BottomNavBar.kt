package com.example.locationtracker.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.locationtracker.navigation.HistoryScreenRoute
import com.example.locationtracker.navigation.HomeScreenRoute
import com.example.locationtracker.navigation.MapScreenRoute

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = when (item.route) {
                    MapScreenRoute ->
                        currentDestination?.hasRoute<MapScreenRoute>() == true

                    HistoryScreenRoute ->
                        currentDestination?.hasRoute<HistoryScreenRoute>() == true

                    HomeScreenRoute ->
                        currentDestination?.hasRoute<HomeScreenRoute>() == true

                    else -> false
                },
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}
