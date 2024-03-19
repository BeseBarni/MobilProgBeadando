package com.example.mobilprogbeadando.presentation.ui.components.plants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.Plant

@Composable
fun PlantQuestListItem(plant: Plant, onClick : (plant : Plant) -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(24.dp)


    ){
        Icon(painter = painterResource(id = R.drawable.water_drop), contentDescription = "", tint = Color(0xFF3F3CDD)
        , modifier = Modifier.size(32.dp).weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(5f)
        ){
            Text(
                text = plant.name,
                color = Color(0xFF261736),
                fontWeight = FontWeight(600),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Water",
                color = Color(0xFF261736),
                fontWeight = FontWeight(500),
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier

                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFFE1DFFB))
                .padding(4.dp)
                .clickable(onClick = {
                    onClick(plant)
                })
                .weight(1f)

        ){
            Icon(
                Icons.Default.Done,"",
                tint = Color(0xFF1CD279),
                modifier = Modifier.size(32.dp)
            )
        }

    }
}