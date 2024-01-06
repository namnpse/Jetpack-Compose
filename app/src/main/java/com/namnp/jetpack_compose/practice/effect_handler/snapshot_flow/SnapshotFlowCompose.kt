package com.namnp.jetpack_compose.practice.effect_handler.snapshot_flow

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun SnapshotFlowCompose() {
    val snackBarHostState = remember { SnackbarHostState() }

    // use to construct flow based on state (not use much in practical)
    LaunchedEffect(key1 = snackBarHostState) {
        snapshotFlow { snackBarHostState }
            .mapNotNull { it.currentSnackbarData?.visuals?.message }
            .distinctUntilChanged()
            .collect { message ->
                println("Snackbar was shown with message: $message")
            }
    }
}