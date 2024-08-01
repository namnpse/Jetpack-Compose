package com.namnp.jetpack_compose.pull_to_refresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewFeedWithPullToRefresh() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val items = remember {
            (1..100).map { "Item $it" }
        }
        var isRefreshing by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PullToRefreshLazyColumn(
                items = items,
                content = { itemTitle ->
                    Text(
                        text = itemTitle,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                },
                isRefreshing = isRefreshing,
                onRefresh = {
                    scope.launch {
                        isRefreshing = true
                        delay(2000L) // Simulated API call
                        isRefreshing = false
                    }
                }
            )
            Button(
                onClick = {
                    isRefreshing = true
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "Refresh")
            }
        }
    }
}