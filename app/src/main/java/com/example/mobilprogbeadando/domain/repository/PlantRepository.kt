package com.example.mobilprogbeadando.domain.repository

import com.example.mobilprogbeadando.data.plants.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    fun getAllPlantsStream() : Flow<List<Plant>>

    fun getPlantsByLocationId(locationId: Int) : Flow<List<Plant>>

    fun getQuestPlants() : Flow<List<Plant>>

    suspend fun upsertPlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)

}