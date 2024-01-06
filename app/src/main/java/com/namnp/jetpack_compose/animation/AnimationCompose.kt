package com.namnp.jetpack_compose.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimationCompose() {
    var sizeBoxState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        label = "",
        targetValue = sizeBoxState,
        animationSpec = tween(
            durationMillis = 3000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing,
        )
        /*animationSpec = spring(
            Spring.DampingRatioLowBouncy
        )*/
        // keyframes: config exactly at what time has what value, how easing
        /*animationSpec = keyframes {
            durationMillis = 5000
            sizeBoxState at 0 with LinearEasing
            sizeBoxState * 1.5f at 1000 with FastOutSlowInEasing
            sizeBoxState * 2f at 5000
        }*/
    )

    val infiniteTransition = rememberInfiniteTransition(label = "infinite_transition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "phase1",
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {
            sizeBoxState += 20.dp
        }) {
            Text(text = "Click here")
        }
    }
}