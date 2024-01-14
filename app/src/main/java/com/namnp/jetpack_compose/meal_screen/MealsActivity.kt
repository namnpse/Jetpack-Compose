package com.namnp.jetpack_compose.meal_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.namnp.jetpack_compose.meal_screen.meal_details.MealDetailsScreen
import com.namnp.jetpack_compose.meal_screen.meal_details.MealDetailsViewModel
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

class MealsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                MealsApp()
            }
        }
    }
}

@Composable
private fun MealsApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "meals_list") {
        composable(route = "meals_list") {
            MealsCategoriesScreen { navigationMealId ->
                navController.navigate("meal_details/$navigationMealId")
            }
        }
        composable(
            route = "meal_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }
}