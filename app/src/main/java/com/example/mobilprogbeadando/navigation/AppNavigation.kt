package com.example.mobilprogbeadando.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobilprogbeadando.presentation.ui.PlantsViewModel
import com.example.mobilprogbeadando.presentation.ui.WeatherViewModel
import com.example.mobilprogbeadando.presentation.ui.screens.HomeScreen
import com.example.mobilprogbeadando.presentation.ui.screens.PlantScreen
import com.example.mobilprogbeadando.presentation.ui.screens.PlantWizardScreen

@Composable
fun AppNavigation(viewModel: PlantsViewModel){
    val navController : NavHostController = rememberNavController()
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF222145), Color(0xFF261736)
        ))
    }

    Scaffold(
        containerColor = Color(0xFFEEECFF),
        modifier = Modifier.background(Color.Transparent),
        bottomBar = {
            Box (
                modifier = Modifier
                    .background(Color.Transparent)

            ){
                NavigationBar(
                    containerColor = Color(0xFF261736),
                    modifier = Modifier
                        .height(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                    ,

                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination : NavDestination? = navBackStackEntry?.destination

                    listOfNavItems.forEach {
                            navItem: NavItem ->
                        NavigationBarItem(

                            colors = colors(selectedIconColor = Color(0xFFF9C80E), unselectedIconColor = Color.White, indicatorColor = Color(0xFF222145)),
                            selected = currentDestination?.hierarchy?.any {it.route == navItem.route} == true,
                            onClick = {
                                navController.navigate(navItem.route){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                if(navItem.icon == null){
                                    Icon(painter = navItem.drawableIcon!!.asPainterResource(), contentDescription = null)
                                }
                                else{
                                    Icon(imageVector = navItem.icon, contentDescription = null)
                                }
                                }

                        )
                    }
                }
            }

    }) {
        paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.HomeScreen.name,
                modifier = Modifier.padding(paddingValues)){
                composable(route = Screens.HomeScreen.name) {
                    HomeScreen()
                }
                composable(route = Screens.PlantScreen.name) {
                    PlantScreen(viewModel)
                }
                composable(route = Screens.PlantWizardScreen.name) {
                    PlantWizardScreen()
                }
    }
    }
}