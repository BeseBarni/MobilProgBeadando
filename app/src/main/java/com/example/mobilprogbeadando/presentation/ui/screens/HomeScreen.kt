package com.example.mobilprogbeadando.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import com.example.mobilprogbeadando.presentation.ui.WeatherViewModel
import com.example.mobilprogbeadando.presentation.ui.components.plants.PlantQuestListItem
import com.example.mobilprogbeadando.presentation.ui.components.weather.WeatherInfoCard

@Composable
fun HomeScreen(viewModel: WeatherViewModel = hiltViewModel(), plantViewModel : PlantsViewModel = hiltViewModel()) {



    var state =
        viewModel.state
    var questPlants = plantViewModel.getQuestPlants().collectAsState(initial = emptyList())
    
    LaunchedEffect(Unit) {
        viewModel.loadWeatherInfo()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            ,
    ){

        WeatherInfoCard(state = state)
        Text(
            text = "Quests",
            color = Color(0xFF261736),
            fontWeight = FontWeight(600),
            fontSize = 28.sp,
            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)

        )
        for (plant in questPlants.value){
            PlantQuestListItem(modifier = Modifier.padding(16.dp),plant = plant, onClick = {
                plantViewModel.waterPlant(it)
            })
        }
    }
}