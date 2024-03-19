package com.example.mobilprogbeadando.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Upsert
    suspend fun upsert(plant : Plant)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("SELECT * FROM plants ORDER BY name")
    fun getAllPlants() : Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE locationId = :locationId ORDER BY name")
    fun getPlantsByLocation(locationId : Int) : Flow<List<Plant>>

    @Query("SELECT * FROM plants ORDER BY name")
    fun getQuestPlants() : Flow<List<Plant>>
}