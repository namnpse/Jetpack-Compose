package com.namnp.jetpack_compose.practice.effect_handler.side_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectCompose(nonComposeCounter: Int) {
    // - call NON-suspend funs
    // - don't have params
    SideEffect {
        println("Called after every successful recomposition") // non-suspend func
    }
}