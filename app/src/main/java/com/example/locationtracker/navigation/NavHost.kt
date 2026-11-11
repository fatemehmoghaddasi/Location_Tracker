package com.example.locationtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locationtracker.ui.screens.LocationPermission
import kotlinx.serialization.Serializable

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LocationPermissionRoute
    ) {

        //composable<SplashScreenRoute> {
        // SplashScreen()
        //}
        composable<LocationPermissionRoute> {
            LocationPermission()
        }
    }
}


//@Serializable
//data object SplashScreenRoute

@Serializable
data object LocationPermissionRoute