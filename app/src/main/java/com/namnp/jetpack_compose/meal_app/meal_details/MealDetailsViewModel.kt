package com.namnp.jetpack_compose.meal_app.meal_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.namnp.jetpack_compose.meal_app.model.MealsRepository
import com.namnp.jetpack_compose.meal_app.model.response.MealResponse

class MealDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repository: MealsRepository = MealsRepository.getInstance()

    var mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""
        mealState.value = repository.getMeal(mealId)
    }

}