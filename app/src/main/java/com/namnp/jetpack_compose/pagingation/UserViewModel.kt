package com.namnp.jetpack_compose.pagingation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {

    private val userRepository = UserRepository()

    var state by mutableStateOf(ScreenState())

    private val paginator = UserPaginator<Int, User>(
        initialKey = state.page,
        onLoadingData = { isLoading ->
            state = state.copy(isLoading = isLoading)
        },
        onRequestData = { currentPage ->
            userRepository.getUsers(currentPage, 20)
        },
        onSuccess = { items, newPage ->
            state = state.copy(
                users = state.users + items,
                page = newPage,
                isReachedEnd = items.isEmpty()
            )
        },
        onError = {
            state = state.copy(errorMessage = it?.localizedMessage)
        },
        getNextKey = {
            state.page + 1
        }
    )

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null,
    val isReachedEnd: Boolean = false,
    val page: Int = 0
)