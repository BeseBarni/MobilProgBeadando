package com.example.mobilprogbeadando.data.repository

import com.example.mobilprogbeadando.data.dao.PlantLocationDao
import com.example.mobilprogbeadando.data.plants.PlantLocation
import com.example.mobilprogbeadando.domain.repository.PlantLocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantLocationRepositoryImpl @Inject constructor (private val plantLocationDao: PlantLocationDao) : PlantLocationRepository {

    override fun getAllLocations(): Flow<List<PlantLocation>> = plantLocationDao.getLocations()

    override suspend fun upsertLocation(plantLocation: PlantLocation) = plantLocationDao.upsert(plantLocation)

    override suspend fun deleteLocation(plantLocation: PlantLocation) = plantLocationDao.delete(plantLocation)

}