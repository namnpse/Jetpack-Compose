package com.namnp.jetpack_compose.practice.effect_handler.launched_effect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel: ViewModel() {
    private val _sharedFlow = MutableSharedFlow<ScreenEvent>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _snackbarCount = MutableStateFlow(0)
    val snackbarCount = _snackbarCount.asStateFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvent.ShowSnackbar("Hello Namnpse"))
        }
    }

    fun startCount() {
        viewModelScope.launch {
            var displayCount = 1
            while (displayCount < 3) {
                delay(1000L)
                displayCount += 1
                _snackbarCount.value = displayCount
            }
        }
    }
}

sealed interface ScreenEvent {
    data class ShowSnackbar(val message: String): ScreenEvent
    data class Navigate(val route: String): ScreenEvent
}