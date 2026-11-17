package com.example.locationtracker.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationtracker.data.repository.LocationRepository
import com.example.locationtracker.ui.screens.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val locationRepository: LocationRepository) :
    ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        getLastLocation()
    }

    private fun getLastLocation() {
        viewModelScope.launch {
            locationRepository.getAllLocations().collect { locations ->
                _uiState.update {
                    it.copy(
                        currentLocation = locations.firstOrNull()
                    )
                }
            }
        }
    }
}

