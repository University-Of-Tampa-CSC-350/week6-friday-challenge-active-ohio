package com.example.fc_006.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fc_006.model.Asteroid
import com.example.fc_006.network.NasaNeoApi
import kotlinx.coroutines.launch

sealed class AsteroidUiState {
    object Idle : AsteroidUiState()
    object Loading : AsteroidUiState()
    data class Success(val asteroid: Asteroid) : AsteroidUiState()
    data class Error(val message: String) : AsteroidUiState()
}

class AsteroidViewModel : ViewModel() {

    private val _uiState = MutableLiveData<AsteroidUiState>(AsteroidUiState.Idle)
    val uiState: LiveData<AsteroidUiState> = _uiState

    fun scanForAsteroids(apiKey: String) {
        viewModelScope.launch {
            _uiState.value = AsteroidUiState.Loading
            try {
                val response = NasaNeoApi.service.getAsteroids(apiKey)
                if (response.nearEarthObjects.isNotEmpty()) {
                    val randomAsteroid = response.nearEarthObjects.random()
                    _uiState.value = AsteroidUiState.Success(randomAsteroid)
                } else {
                    _uiState.value = AsteroidUiState.Error("No asteroids found.")
                }
            } catch (e: Exception) {
                _uiState.value = AsteroidUiState.Error("Mission Control lost asteroid tracking data.")
            }
        }
    }
}
