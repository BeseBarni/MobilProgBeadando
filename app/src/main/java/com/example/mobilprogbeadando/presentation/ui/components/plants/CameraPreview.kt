package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCapture.OnImageSavedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.helpers.IconResource
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@Composable
fun CameraPreview(
    infoText : String,
    pictureName : String, viewModel : PlantsViewModel, showDialog : MutableState<Boolean>, onCameraClose : () -> Unit
) {
    viewModel.imageUri.value = Uri.EMPTY
    // 1
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }


    // 2


    // 3
    Box( modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
                PreviewView(context).apply {

                }.also{
                    it.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
        }, modifier = Modifier.fillMaxSize())
        IconButton(
            modifier = Modifier
                .padding(24.dp),
            onClick = {
                onCameraClose()
            }
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close picture",
                tint = Color(0xFFF9C80E),
                modifier = Modifier
                    .size(96.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(24.dp,24.dp,0.dp,0.dp)
                )
                .background(Color(0xFFF3F4FD))
        ) {
            Text(textAlign = TextAlign.Center ,text = infoText, color = Color(0xFF261736), fontWeight = FontWeight(500), fontSize = 20.sp, modifier = Modifier.padding(top = 24.dp,start = 24.dp, end = 24.dp))
            Text(textAlign = TextAlign.Center, text = "Have adequate lighting levels", color = Color(0xFF261736), fontWeight = FontWeight(500), fontSize = 20.sp, modifier = Modifier.padding(bottom = 32.dp,start = 24.dp, end = 24.dp))
            IconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFF9C80E),

                    ),
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .size(64.dp)

                ,
                onClick = {
                    Log.i("kilo", "ON CLICK")
                    val mainExecutor = ContextCompat.getMainExecutor(context)

                    val contentValues = ContentValues()
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, pictureName)
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

                    val options = ImageCapture.OutputFileOptions.Builder(
                        context.contentResolver,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                    ).build()

                    cameraController.takePicture(options,mainExecutor,object : OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            viewModel.imageUri.value =  outputFileResults.savedUri!!

                            onCameraClose()
                            showDialog.value = true
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Log.e("imageError",exception.message.toString())

                        }
                    })
                },
                content = {
                    Icon(
                        painter = IconResource.fromDrawableResource(R.drawable.camera_icon).asPainterResource(),
                        contentDescription = "Take picture",
                        tint = Color(0xFF261736),
                        modifier = Modifier
                            .size(42.dp)
                    )
                }
            )
        }

    }
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

private fun takePhoto(
    filenameFormat: String,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {

    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(outputOptions, executor, object: ImageCapture.OnImageSavedCallback {
        override fun onError(exception: ImageCaptureException) {
            Log.e("kilo", "Take photo error:", exception)
            onError(exception)
        }

        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = Uri.fromFile(photoFile)
            onImageCaptured(savedUri)
        }
    })
}