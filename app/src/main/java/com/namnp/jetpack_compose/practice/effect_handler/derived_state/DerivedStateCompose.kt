package com.namnp.jetpack_compose.practice.effect_handler.derived_state

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DerivedStateCompose() {
    var counter by remember {
        mutableIntStateOf(0)
    }

    // use when there is complex computation that depends on state that changes overtime
//    val counterText = "The counter is $counter" BAD
    val counterText by remember {
        derivedStateOf { // re-compute when counter state changes
            "The counter is $counter" // access the first time -> then cache the computation
        }
    }
    Button(onClick = {
        counter++
    }) {
        Text(text = counterText) // counterText will get the cached data, dont have to compute "The counter is $counter" again
    }
}