package com.example.locationtracker.ui.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationtracker.data.repository.LocationRepository
import com.example.locationtracker.ui.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MapScreenViewModel@Inject constructor(
private val locationRepository: LocationRepository
): ViewModel() {
val locations : StateFlow<List<LocationModel>> = locationRepository.getAllLocations()
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}