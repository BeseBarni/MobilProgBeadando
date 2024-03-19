package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.data.plants.PlantLocation
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun LocationDialog(selectedLocation : PlantLocation, showDialog : MutableState<Boolean>, viewModel: PlantsViewModel = hiltViewModel()) {

    var plants = viewModel.getPlantsByLocationId(selectedLocation.id!!).collectAsState(initial = emptyList())
    var showPlantAddDialog = remember { mutableStateOf(false ) }
    var showPlantDialog = remember { mutableStateOf(false ) }


    if(showDialog.value){
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                showDialog.value = false
            },

            ){
            Scaffold(
                containerColor = Color.Transparent,
                modifier = Modifier
                    .fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.imageUri.value = Uri.EMPTY
                            showPlantAddDialog.value = true
                        },
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                },
                content = {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFF3F4FD))

                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(0.dp,12.dp,0.dp,10.dp)
                        ){
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                ),
                                onClick = { showDialog.value = false }
                            ) {
                                Icon(Icons.Filled.Close, "", tint = Color(0xFFF9C80E), modifier = Modifier.size(32.dp))
                            }
                            Text(
                                text = selectedLocation.name,
                                color = Color(0xFF261736),
                                fontWeight = FontWeight(500),
                                fontSize = 24.sp
                            )
                            PaddingValues(32.dp,32.dp)
                        }
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
                                for (plant in plants.value){
                                    Box(
                                        modifier = Modifier.padding(0.dp,8.dp,0.dp,8.dp)
                                    ){
                                        PlantListItem(
                                            plant = plant,
                                            onDelete = {
                                                viewModel.deletePlant(plant)
                                            },
                                            onClick = {
                                                showPlantDialog.value = true
                                                viewModel.setSelectedPlant(plant)

                                            })
                                    }
                                }
                            }                }

                    }
                    if(showPlantDialog.value){
                        PlantDialog(viewModel,showPlantDialog)
                    }
                    PlantAddDialog(locationId = selectedLocation.id,onDismissRequest = {
                        if(it != null) {
                            viewModel.addPlant(it)
                        }
                    },showDialog = showPlantAddDialog, viewModel = viewModel)
                }
            )

        }

    }

}