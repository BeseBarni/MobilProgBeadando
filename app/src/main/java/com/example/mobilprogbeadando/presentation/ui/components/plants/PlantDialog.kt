package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel

@Composable
fun PlantDialog(viewModel : PlantsViewModel, showDialog : MutableState<Boolean>) {
    var selectedPlant = viewModel.selectedPlant.collectAsState()

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                showDialog.value = false
            },

            ){
            Column(
                modifier = Modifier

                    .background(Color(0xFFF3F4FD))
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(0.dp,12.dp,0.dp,0.dp)
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
                        text = selectedPlant.value!!.name,
                        color = Color(0xFF261736),
                        fontWeight = FontWeight(500),
                        fontSize = 24.sp
                    )
                    PaddingValues(16.dp,16.dp)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp, 32.dp, 32.dp, 0.dp)
                        .verticalScroll(rememberScrollState())
                    ,
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                Uri.parse(selectedPlant.value!!.imagePath))
                            .placeholder(R.drawable.location_placeholder)
                            .build(), "", contentScale = ContentScale.Crop, modifier = Modifier
                            .padding(12.dp)
                            .height(256.dp)
                            .width(256.dp)
                            .clip(RoundedCornerShape(16.dp))
                            )
                    PlantXpBar(selectedPlant.value!!.xp)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(64.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    ){
                        Text(
                            textAlign = TextAlign.Center,
                            text = selectedPlant.value!!.description,
                            color = Color(0xFF261736),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(64.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    ){
                        Text(
                            textAlign = TextAlign.Center,
                            text = selectedPlant.value!!.name,
                            color = Color(0xFF261736),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(64.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    ){
                        Text(
                            textAlign = TextAlign.Center,
                            text = selectedPlant.value!!.type,
                            color = Color(0xFF261736),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(64.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    ){
                        Text(
                            textAlign = TextAlign.Center,
                            text = "Watering interval: ${selectedPlant.value!!.wateringInterval.toString()} days",
                            color = Color(0xFF261736),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                    }
     }
            }


    }
}