package com.example.locationtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locationtracker.ui.screens.HomeScreen
import com.example.locationtracker.ui.screens.LocationPermission
import com.example.locationtracker.ui.screens.SplashScreen
import kotlinx.serialization.Serializable

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute
    ) {

        composable<SplashScreenRoute> {
            SplashScreen(
                onNavigateToHome = { navController.navigate(HomeScreenRoute) }
            )
        }
        composable<HomeScreenRoute> {
            HomeScreen()
        }
        composable<LocationPermissionRoute> {
            LocationPermission(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}


@Serializable
data object SplashScreenRoute

@Serializable
data object HomeScreenRoute

@Serializable
data object LocationPermissionRoute