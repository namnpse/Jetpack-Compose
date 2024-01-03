package com.namnp.jetpack_compose.meal_app.model

import com.namnp.jetpack_compose.meal_app.model.api.MealsWebService
import com.namnp.jetpack_compose.meal_app.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
    }
}