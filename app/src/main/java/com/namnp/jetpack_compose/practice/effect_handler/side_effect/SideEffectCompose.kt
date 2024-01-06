package com.namnp.jetpack_compose.practice.effect_handler.side_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectCompose(nonComposeCounter: Int) {
    SideEffect {
        println("Called after every successful recomposition")
    }
}