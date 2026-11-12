package com.example.locationtracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.locationtracker.ui.screens.HistoryScreen
import com.example.locationtracker.ui.screens.HomeScreen
import com.example.locationtracker.ui.screens.LocationPermission
import com.example.locationtracker.ui.screens.MapScreen
import com.example.locationtracker.ui.screens.SplashScreen
import kotlinx.serialization.Serializable

@Composable
fun Nav(
    navController : NavHostController,
    modifier: Modifier = Modifier
) {

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
        composable<MapScreenRoute>{
            MapScreen()
        }
        composable<HistoryScreenRoute>{
            HistoryScreen()
        }
    }
}


@Serializable
data object SplashScreenRoute

@Serializable
data object HomeScreenRoute

@Serializable
data object LocationPermissionRoute

@Serializable
data object MapScreenRoute

@Serializable
data object HistoryScreenRoute