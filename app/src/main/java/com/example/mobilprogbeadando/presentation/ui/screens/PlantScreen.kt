package com.example.mobilprogbeadando.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.domain.plants.PlantScreenSortType
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import com.example.mobilprogbeadando.presentation.ui.components.plants.SwitchTab
import com.example.mobilprogbeadando.presentation.ui.components.plants.PlantScreenList
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlantScreen(viewModel : PlantsViewModel = hiltViewModel()) {

    val plants = viewModel.plants.collectAsState(initial = emptyList())
    val locations = viewModel.locations.collectAsState(initial = emptyList())
    val sortState = remember {
        mutableStateOf<PlantScreenSortType>(PlantScreenSortType.Location)
    }

    Scaffold(topBar = {
        SwitchTab(sortState)
    },
        content = {
                  PlantScreenList(plants = plants.value, locations = locations.value, sortState.value, viewModel)
        },
        containerColor = Color.Transparent)

}

