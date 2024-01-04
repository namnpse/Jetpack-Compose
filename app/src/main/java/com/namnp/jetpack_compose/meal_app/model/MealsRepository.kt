package com.namnp.jetpack_compose.meal_app.model

import com.namnp.jetpack_compose.meal_app.model.api.MealsWebService
import com.namnp.jetpack_compose.meal_app.model.response.MealResponse
import com.namnp.jetpack_compose.meal_app.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}