package com.example.mobilprogbeadando.data.repository

import com.example.mobilprogbeadando.data.dao.PlantDao
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(private val plantDao: PlantDao) : PlantRepository {

    override fun getAllPlantsStream(): Flow<List<Plant>> = plantDao.getAllPlants()

    override fun getPlantsByLocationId(locationId: Int): Flow<List<Plant>> = plantDao.getPlantsByLocation(locationId)
    override fun getQuestPlants(): Flow<List<Plant>> = plantDao.getQuestPlants()

    override suspend fun upsertPlant(plant: Plant) = plantDao.upsert(plant)

    override suspend fun deletePlant(plant: Plant) = plantDao.delete(plant)
    override suspend fun deleteByLocation(locationId: Int) = plantDao.deleteByLocation(locationId)
}