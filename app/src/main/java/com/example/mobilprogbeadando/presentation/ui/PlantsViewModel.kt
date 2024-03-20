package com.example.mobilprogbeadando.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation
import com.example.mobilprogbeadando.domain.repository.PlantLocationRepository
import com.example.mobilprogbeadando.domain.repository.PlantRepository
import com.example.mobilprogbeadando.domain.repository.TextAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(private val plantsLocationRepository: PlantLocationRepository, private val plantRepository: PlantRepository,private val repository: TextAiRepository)  : ViewModel() {

    var shouldShowCamera: MutableStateFlow<Boolean> = MutableStateFlow( false)
    var showPlantsDialog: MutableStateFlow<Boolean> = MutableStateFlow( false)

    var showPlantAddDialog: MutableStateFlow<Boolean> = MutableStateFlow( false)

    var selectedLocation : MutableStateFlow<PlantLocation?> = MutableStateFlow(null)
    var selectedPlant : MutableStateFlow<Plant?> = MutableStateFlow(null)

    var imageUri : MutableState<Uri> = mutableStateOf(Uri.EMPTY)

    var state by mutableStateOf(PlantsState())
        private set

    var plants : Flow<List<Plant>> = plantRepository.getAllPlantsStream()


    var locations :Flow<List<PlantLocation>> = plantsLocationRepository.getAllLocations()

    fun openOrCloseCamera(){
        Log.i("camera",shouldShowCamera.value.toString())
        shouldShowCamera.value = !shouldShowCamera.value
    }

    fun addPlant(plant: Plant, traits : List<String>, plantLocation: PlantLocation) {
        viewModelScope.launch {
            val description = repository.getDescription(plant.name,traits).data.outputs[0].text
            plant.description = description
            plantRepository.upsertPlant(plant)
            plantLocation.numberOfPlants++
            plantsLocationRepository.upsertLocation(plantLocation)
        }
    }

    fun waterPlant(plant : Plant){
        plant.lastWatered = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
        plant.xp += 60
        viewModelScope.launch {
            plantRepository.upsertPlant(plant)
        }
    }

    fun getPlantsByLocationId(id : Int) : Flow<List<Plant>>{
        return plantRepository.getPlantsByLocationId(id)
    }

    fun getQuestPlants() : Flow<List<Plant>> {
        return plantRepository.getQuestPlants().map{
            it.filter{
                it.lastWatered < Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).minusDays(it.wateringInterval.toLong()).toInstant())
            }
        }
    }

    fun setSelectedLocation(plantLocation: PlantLocation){
        selectedLocation.value = plantLocation
    }

    fun setSelectedPlant(plant: Plant){
        selectedPlant.value = plant
    }

    fun addLocation(plantLocation: PlantLocation){
        viewModelScope.launch {
            plantsLocationRepository.upsertLocation(
                plantLocation
            )
        }
    }

    fun deleteLocation(plantLocation: PlantLocation){
        viewModelScope.launch {
            plantRepository.deleteByLocation(plantLocation.id!!)
            plantsLocationRepository.deleteLocation(plantLocation)

        }
    }

    fun deletePlant(plant: Plant, plantLocation: PlantLocation){
        viewModelScope.launch {
            plantRepository.deletePlant(plant)
            plantLocation.numberOfPlants--
            plantsLocationRepository.upsertLocation(plantLocation)
        }
    }
}