package com.namnp.jetpack_compose.practice.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateViewModel: ViewModel() {
    val textFieldState = MutableLiveData("")

    fun onTextFieldChange(value: String) {
        textFieldState.value = value
    }
}