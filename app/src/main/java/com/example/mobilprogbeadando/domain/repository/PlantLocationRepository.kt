package com.example.mobilprogbeadando.domain.repository

import com.example.mobilprogbeadando.data.plants.PlantLocation
import kotlinx.coroutines.flow.Flow

interface PlantLocationRepository {

    fun getAllLocations() : Flow<List<PlantLocation>>

    suspend fun upsertLocation(plantLocation: PlantLocation)

    suspend fun deleteLocation(plantLocation: PlantLocation)
}