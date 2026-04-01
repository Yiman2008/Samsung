package com.example.project.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project.ui.screens.HomeScreen
import com.example.project.ui.screens.ResultScreen
import com.example.project.ui.screens.TabletDetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
    object Result : Screen("result")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetail = { navController.navigate(Screen.Detail.route) },
                onCheckClick = { navController.navigate(Screen.Result.route) }
            )
        }
        composable(Screen.Detail.route) {
            TabletDetailScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Result.route) {
            ResultScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}