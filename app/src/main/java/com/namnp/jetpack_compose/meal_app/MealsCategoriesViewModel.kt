package com.namnp.jetpack_compose.meal_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namnp.jetpack_compose.meal_app.model.MealsRepository
import com.namnp.jetpack_compose.meal_app.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {

    init {
        viewModelScope.launch {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState = mutableStateOf(emptyList<MealResponse>())

    private suspend fun getMeals(): List<MealResponse> = withContext(Dispatchers.IO) {
        return@withContext repository.getMeals().categories
    }
}