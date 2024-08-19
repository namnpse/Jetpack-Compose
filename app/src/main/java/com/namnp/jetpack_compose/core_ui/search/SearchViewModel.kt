package com.namnp.jetpack_compose.core_ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _searchWidgetState: MutableState<SearchViewState> = mutableStateOf(value = SearchViewState.CLOSED)
    val searchWidgetState: State<SearchViewState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchViewState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

}