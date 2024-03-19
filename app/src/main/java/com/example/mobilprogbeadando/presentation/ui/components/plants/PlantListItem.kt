package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.Plant
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantListItem(plant: Plant, onDelete : (plant : Plant) -> Unit, onClick : (plant : Plant) -> Unit) {

    val haptic = LocalHapticFeedback.current
    var isEnabled by remember { mutableStateOf(false) }

        Box(
         modifier = Modifier
             .fillMaxSize()
             .padding(8.dp)
             .clip(RoundedCornerShape(8.dp))
             .background(Color.White)
             .combinedClickable(onLongClick = {
                 isEnabled = true
                 haptic.performHapticFeedback(HapticFeedbackType.LongPress)
             }, onClick = {
                 haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick(plant)
             })
        ){
            Row(){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(plant.imagePath))
                        .placeholder(R.drawable.location_placeholder)
                        .build(), "", contentScale = ContentScale.Crop, modifier = Modifier
                        .padding(12.dp)
                        .height(96.dp)
                        .width(96.dp)
                        .clip(RoundedCornerShape(96.dp))
                        .clip(RoundedCornerShape(16.dp)),

                    alignment = Alignment.CenterStart,
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = plant.name,
                        color = Color(0xFF261736),
                        fontWeight = FontWeight(600),
                        fontSize = 28.sp,
                        lineHeight = 38.sp
                    )
                    Text(
                        text = "Last watered: 5 days ago",
                        color = Color(0xFF261736),
                        modifier = Modifier
                            .align(Alignment.End)
                    )
                }
            }
            if(isEnabled){
                Icon(
                    Icons.Default.Close,"",
                    tint = Color.White,
                    modifier = Modifier
                        .absoluteOffset(8.dp, -8.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Red)
                        .clickable(onClick = {
                            onDelete(plant)
                        })
                        .align(Alignment.TopEnd)
                )
                LaunchedEffect(Unit) {
                    delay(5000)
                    isEnabled = false
                }
            }
    }
}