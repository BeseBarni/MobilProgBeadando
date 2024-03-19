package com.example.mobilprogbeadando.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantLocationDao {

    @Upsert
    suspend fun upsert(plantLocation: PlantLocation)

    @Delete
    suspend fun delete(plantLocation: PlantLocation)

    @Query("SELECT * FROM locations ORDER BY name")
    fun getLocations() : Flow<List<PlantLocation>>

}