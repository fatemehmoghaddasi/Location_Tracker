package com.example.locationtracker.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationtracker.data.repository.LocationRepository
import com.example.locationtracker.ui.model.LocationModel
import com.example.locationtracker.ui.screens.history.model.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryScreenViewModel@Inject constructor (
    private val locationRepository: LocationRepository
): ViewModel(){
    private val _uiState : MutableStateFlow<HistoryUiState> = MutableStateFlow(HistoryUiState())
    val uiState : StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init{
        getAllLocations()
    }
    private fun getAllLocations(){
        viewModelScope.launch {
            locationRepository.getAllLocations().collect { locations ->
                _uiState.update {
                    it.copy(locations = locations)
                }
            }
        }
    }
     fun deleteLocation(location: LocationModel) {
        viewModelScope.launch {
            locationRepository.deleteLocation(location)
        }
    }
}