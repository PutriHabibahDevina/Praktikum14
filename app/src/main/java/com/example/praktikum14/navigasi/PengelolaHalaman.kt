package com.example.praktikum14.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.praktikum14.ui.view.DetailScreen
import com.example.praktikum14.ui.view.HomeScreen
import com.example.praktikum14.ui.view.InsertView

@Composable
fun PengelolaHalaman (
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                }
            )
        }

        composable(DestinasiInsert.route) {
            InsertView(
                onBack = {navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiDetail.route)
                }
            )
        }

        composable(DestinasiDetail.route) {
            DetailScreen(
                onBack = {navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiInsert.route)
                }
            )
        }
    }
}