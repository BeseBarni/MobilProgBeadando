package com.example.mobilprogbeadando.data.plants

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class PlantLocation(
    val name : String,
    val numberOfPlants : Int = 0,
    val imagePath : String? = null,
    @PrimaryKey
    val id : Int? = null
)
