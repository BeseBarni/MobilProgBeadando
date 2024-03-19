package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation
import com.example.mobilprogbeadando.domain.plants.PlantScreenSortType
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlantScreenList(plants : List<Plant>, locations : List<PlantLocation>, plantScreenSortType: PlantScreenSortType, viewModel : PlantsViewModel = hiltViewModel()) {

    var showAddDialog = remember { mutableStateOf(false ) }
    var showLocationDialog = remember { mutableStateOf(false ) }
    var showPlantDialog = remember { mutableStateOf(false ) }


    var selectedLocation = viewModel.selectedLocation.collectAsState(initial = null)

    var selectedPlant = viewModel.selectedPlant.collectAsState(initial = null)

    AnimatedVisibility(
        visible = plantScreenSortType == PlantScreenSortType.Location,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.imageUri.value = Uri.EMPTY
                        showAddDialog.value = true
                    },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        ){
            FlowRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 96.dp, 0.dp, 0.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                if(locations == null || locations.isEmpty()){
                    Text(text = "No locations")
                }
                else {
                    for (location in locations){
                        Box(
                            modifier = Modifier
                                .padding(0.dp,8.dp,0.dp,8.dp)

                        ){
                            LocationListItem(id = location.id!!, plantLocation = location, onDelete = {
                                viewModel.deleteLocation(location)
                            }, onClick = {
                                viewModel.setSelectedLocation(location)
                                showLocationDialog.value = true
                            })
                        }

                    }
                }
            }
            LocationAddDialog(onDismissRequest = {
                if(it != null) {
                    viewModel.addLocation(it)
                }
            }, showDialog = showAddDialog , viewModel)
            if(selectedLocation.value != null){
                LocationDialog(selectedLocation = selectedLocation.value!!, showDialog = showLocationDialog, viewModel)
            }

        }
    }

    AnimatedVisibility(
        visible = plantScreenSortType == PlantScreenSortType.Plant,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 96.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
                ,
        ) {
            if(plants == null){
                Text(text = "No Plants")
            }
            else {
                for (plant in plants){
                    Box(
                        modifier = Modifier.padding(0.dp,8.dp,0.dp,8.dp)

                    ){
                        PlantListItem(
                            plant = plant,
                            onDelete = {
                            viewModel.deletePlant(plant)
                        },
                            onClick = {
                                viewModel.setSelectedPlant(plant)
                                showPlantDialog.value = true
                            })
                    }
                }
            }
        }
    }
    if(showPlantDialog.value){
        PlantDialog(viewModel,showPlantDialog)
    }
}