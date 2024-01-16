package com.namnp.jetpack_compose.pagingation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

@Composable
fun UserPaginationCompose() {

    val userViewModel = viewModel<UserViewModel>()
    val state = userViewModel.state
    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = scrollState) {
        // condition to check if scrolled to end
        val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()
            ?: return@LaunchedEffect
//        scrollState.canScrollForward // need to check
        if (lastVisibleItem.index == state.users.size - 1 && !state.isReachedEnd && !state.isLoading) {
            userViewModel.loadUsers()
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.users.size) { i ->
            val item = state.users[i]
            /*if (i >= state.users.size - 1 && !state.isReachedEnd && !state.isLoading) {
                userViewModel.loadUsers()
            }*/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Name: ${item.name}",
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Address: ${item.address}")
            }
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserPaginationPreview() {
    JetpackComposeTheme {
        UserPaginationCompose()
    }
}