package com.example.locationtracker.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.locationtracker.navigation.LocationPermissionRoute
import com.example.locationtracker.navigation.Nav
import com.example.locationtracker.navigation.SplashScreenRoute
import com.example.locationtracker.ui.components.BottomNavBar

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar =
        currentDestination != null &&
                !currentDestination.hasRoute<SplashScreenRoute>() &&
                !currentDestination.hasRoute<LocationPermissionRoute>()
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->

        Nav(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
