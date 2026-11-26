package com.example.locationtracker.ui.screens.history.model

import com.example.locationtracker.ui.model.LocationModel

data class HistoryUiState(
    val locations : List<LocationModel> = emptyList()
)
