package com.example.locationtracker.ui.screens.home.model

import com.example.locationtracker.ui.model.LocationModel

data class HomeUiState(
    val isTracking : Boolean = false,
    val currentLocation : LocationModel? = null
)
