package com.example.mobilprogbeadando.presentation.ui

import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation

data class PlantsState(
    val plants : List<Plant>? = null,
    val locations : List<PlantLocation>? = null,
    val isLoading : Boolean = false,
    val error : String? = null
)