package com.namnp.jetpack_compose.practice.effect_handler.produce_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun produceStateCompose(countUpTo: Int): State<Int> {
    // use to produce state over time -> like flow -> emit multiple values
    return produceState(initialValue = 0) {
        while(value < countUpTo) {
            delay(1000L)
            value++
        }
    }
    // similar to flow
/*    return flow<Int> {
        var value = 0
        while(value < countUpTo) {
            delay(1000L)
            value++
            emit(value)
        }
    }.collectAsState(initial = 0)*/
}