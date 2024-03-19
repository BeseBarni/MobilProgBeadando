package com.example.mobilprogbeadando.data.plants

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "plants")
data class Plant(
    val name : String,
    val type : String,
    var lastWatered : Date,
    val wateringInterval : Int,
    var xp : Int,
    var imagePath : String? = null,
    val locationId : Int? = null,
    var description : String = "",
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)
