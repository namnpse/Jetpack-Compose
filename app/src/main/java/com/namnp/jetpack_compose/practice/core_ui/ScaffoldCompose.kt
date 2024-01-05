package com.namnp.jetpack_compose.practice.core_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ScaffoldCompose() {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        floatingActionButton = {
            var clickCount by remember {
                mutableIntStateOf(1)
            }
            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar("SnackBar $clickCount")
                        clickCount++
                    }
                },
            ) {
                Text(text = "Show SnackBar")
            }
        }
    ) { innerPadding ->
        Text(
            text = "Body content",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}