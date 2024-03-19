package com.example.mobilprogbeadando.navigation

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mobilprogbeadando.R
import com.example.mobilprogbeadando.helpers.IconResource

data class NavItem(
    val label: String,
    val icon : ImageVector?,
    val route: String,
    val drawableIcon : IconResource? = null
)


val listOfNavItems = listOf(
    NavItem("Home", Default.Home, Screens.HomeScreen.name),
    NavItem("Plants", icon = null, drawableIcon = IconResource.fromDrawableResource(R.drawable.potted_plant), route =Screens.PlantScreen.name))
