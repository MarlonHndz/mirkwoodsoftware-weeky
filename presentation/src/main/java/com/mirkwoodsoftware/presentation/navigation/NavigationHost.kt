package com.mirkwoodsoftware.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mirkwoodsoftware.presentation.ui.FormAddMedicineScreen
import com.mirkwoodsoftware.presentation.ui.HistoryScreen
import com.mirkwoodsoftware.presentation.ui.HomeScreen
import com.mirkwoodsoftware.presentation.ui.MedDetailScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.MedDetail.route) { MedDetailScreen(navController) }
        composable(Screen.FormAddMedicine.route) { FormAddMedicineScreen(navController) }
        composable(Screen.History.route) { HistoryScreen(navController) }
    }
}
