package com.example.locationtracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.locationtracker.ui.screens.LocationPermission
import com.example.locationtracker.ui.screens.MapScreen
import com.example.locationtracker.ui.screens.SplashScreen
import com.example.locationtracker.ui.screens.history.HistoryScreen
import com.example.locationtracker.ui.screens.home.HomeScreen
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
        composable<HomeScreenRoute> { backStackEntry ->

            val permissionGranted by backStackEntry.savedStateHandle
                .getStateFlow("location_permission_granted", false)
                .collectAsStateWithLifecycle()

            HomeScreen(
                onStartTrackingClick = {
                    navController.navigate(LocationPermissionRoute)
                                       },
                    onStopTrackingClick = {
                        // اینجا فعلاً سرویس رو متوقف می‌کنیم
                    }
            )
        }
        composable<LocationPermissionRoute> {
            LocationPermission(
                onBackClick = {
                    navController.navigate(HomeScreenRoute)
                }

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