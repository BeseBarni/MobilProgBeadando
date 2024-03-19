package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilprogbeadando.BuildConfig
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.PlantLocation
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import java.io.File
import java.util.Objects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationAddDialog(onDismissRequest : (plantLocation : PlantLocation?) -> Unit, showDialog : MutableState<Boolean>, viewModel: PlantsViewModel) {

   var name by rememberSaveable {  mutableStateOf("") }
    var shouldShowCamera = remember { mutableStateOf(false ) }
    if(shouldShowCamera.value){
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                showDialog.value = true
            }
        ){
            CameraPreview(infoText = "Make sure location is visible",pictureName = "asd", viewModel = viewModel, showDialog, onCameraClose = {
                shouldShowCamera.value = false
            })
        }

    }

    if(showDialog.value){
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                showDialog.value = false
            },

        ){
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .clip(RoundedCornerShape(8.dp))
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
                            text = "Add Location",
                            color = Color(0xFF261736),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                        PaddingValues(32.dp,32.dp)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(16.dp)
                    ){
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            placeholder = { Text("Kitchen") },
                            singleLine = true
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(viewModel.imageUri.value)
                                .placeholder(R.drawable.location_placeholder)
                                .build(), "", contentScale = ContentScale.Crop, modifier = Modifier
                                .padding(12.dp)
                                .height(256.dp)
                                .width(256.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable(onClick = {
                                    shouldShowCamera.value = true
                                    showDialog.value = false
                                }))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 12.dp)
                    ) {

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9C80E)
                            ),
                            onClick = {
                                showDialog.value = false
                                onDismissRequest(PlantLocation(name, 0, viewModel.imageUri.value.toString()))
                            }
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }


    }




}

