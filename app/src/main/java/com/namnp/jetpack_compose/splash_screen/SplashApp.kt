package com.namnp.jetpack_compose.splash_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashApp() {
    val navController = rememberNavController()
    Navigation(navController = navController)
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}

sealed class Screen(val route: String) {
    data object Splash: Screen("splash")
    data object Main: Screen("main")
}