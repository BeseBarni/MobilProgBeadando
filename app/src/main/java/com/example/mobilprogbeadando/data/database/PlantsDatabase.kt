package com.example.mobilprogbeadando.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilprogbeadando.data.converters.Converters
import com.example.mobilprogbeadando.data.dao.PlantDao
import com.example.mobilprogbeadando.data.dao.PlantLocationDao
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation

@Database(entities = [Plant::class, PlantLocation::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlantsDatabase : RoomDatabase() {

    abstract fun plantDao() : PlantDao

    abstract fun plantLocationDao() : PlantLocationDao

}