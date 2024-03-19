package com.example.mobilprogbeadando.presentation.ui.components.plants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobilprogbeadando.domain.plants.PlantScreenSortType

@Composable
fun SwitchTab(switchState : MutableState<PlantScreenSortType>) {

    fun Color.Companion.ButtonColor(plantScreenSortType: PlantScreenSortType) : Color {
        val color : Color
        if (switchState.value == plantScreenSortType){
            color = Color(0xFFFAD135)
        } else{
            color = Color(0xFFE1DFFB)
        }
        return color
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ){
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            Button(
                onClick = {
                   switchState.value = PlantScreenSortType.Location
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.ButtonColor(PlantScreenSortType.Location)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)

            ) {
                Text(
                    color = Color(0xFF261736),
                    text = "Location"
                )
            }
            Button(
                onClick = {
                    switchState.value = PlantScreenSortType.Plant
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.ButtonColor(PlantScreenSortType.Plant)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    color = Color(0xFF261736),
                    text = "Name"
                )
            }
        }
    }


}

