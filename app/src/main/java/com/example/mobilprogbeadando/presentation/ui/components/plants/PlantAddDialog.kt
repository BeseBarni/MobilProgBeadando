package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.data.plants.Plant
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun PlantAddDialog(locationId : Int, onDismissRequest : (plant : Plant?, traits : List<String>?) -> Unit, showDialog : MutableState<Boolean>, viewModel: PlantsViewModel) {

    var name by rememberSaveable {  mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(false) }
    var type by rememberSaveable {  mutableStateOf("") }
    var isTypeValid by remember { mutableStateOf(false) }
    var wateringInterval by rememberSaveable {  mutableStateOf("") }
    var iswateringIntervalValid by remember { mutableStateOf(false) }

    var traits by rememberSaveable {  mutableStateOf("") }
    var isTraitsValid by remember { mutableStateOf(false) }

    val date = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val pattern = remember { Regex("^\\d+\$") }

    var shouldShowCamera = remember { mutableStateOf(false ) }
    var showInnerDialog = remember { mutableStateOf(true ) }


    if(shouldShowCamera.value){
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                showDialog.value = true
            }
        ){
            CameraPreview(infoText = "Make sure plant is visible",pictureName = "asd", viewModel = viewModel, showDialog, onCameraClose = {
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
                    .verticalScroll(rememberScrollState())
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
                        text = "Add Plant",
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
                        onValueChange = {
                            name = it
                            isNameValid = it.isNotEmpty()
                                        },
                        label = { Text("Name") },
                        placeholder = { Text("Kitchen") },
                        singleLine = true,
                        isError = !isNameValid
                    )
                    if (!isNameValid) {
                        Text(text = "Please enter a name", color = Color.Red)
                    }
                    OutlinedTextField(
                        value = type,
                        onValueChange = {
                            type = it
                            isTypeValid = it.isNotEmpty()
                                        },
                        label = { Text("Type") },
                        placeholder = { Text("Cactus") },
                        singleLine = true,
                        isError = !isTypeValid
                    )
                    if (!isTypeValid) {
                        Text(text = "Please enter a type", color = Color.Red)
                    }
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = wateringInterval,
                        onValueChange = {
                            if(it.isEmpty() || it.matches(pattern)){
                                wateringInterval = it
                                iswateringIntervalValid = true
                            } else{
                                iswateringIntervalValid = false
                            }
                                        },
                        isError = !iswateringIntervalValid
                        ,
                        label = { Text("Watering interval (days)") },
                        placeholder = { Text("12") },
                        singleLine = true
                    )
                    if (!iswateringIntervalValid) {
                        Text(text = "Please enter an interval", color = Color.Red)
                    }
                    OutlinedTextField(
                        value = traits,
                        onValueChange = {
                            traits = it
                            isTraitsValid = it.isNotEmpty()
                        },
                        label = { Text("Personality traits") },
                        placeholder = { Text("Nonchalant, calm") },
                        singleLine = true,
                        isError = !isTraitsValid
                    )
                    if (!isTraitsValid) {
                        Text(text = "Please enter some traits", color = Color.Red)
                    }
                    if(viewModel.imageUri.value == Uri.EMPTY){
                        Image(
                            painter = painterResource(id = R.drawable.plant_placeholder),
                            contentDescription = "Plant placeholder",
                            modifier = Modifier
                                .padding(12.dp)
                                .height(256.dp)
                                .width(256.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable(onClick = {
                                    shouldShowCamera.value = true
                                    showDialog.value = false
                                }))

                    }
                    else{
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(viewModel.imageUri.value)
                                .placeholder(R.drawable.plant_placeholder)
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
                        enabled = isNameValid && isTypeValid && isTraitsValid && iswateringIntervalValid,

                        onClick = {
                            showDialog.value = false
                            var imagePath = ""
                            if(viewModel.imageUri.value != Uri.EMPTY){
                                imagePath = viewModel.imageUri.value.toString()
                            }
                            onDismissRequest(Plant(
                                name = name,
                                type = type,
                                lastWatered = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).minusDays(30).toInstant()),
                                wateringInterval = wateringInterval.toInt(),
                                0,
                                imagePath = viewModel.imageUri.value.toString(),
                                locationId = locationId,
                            ), traits.split(','))
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }


    }




}