package com.namnp.jetpack_compose.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.namnp.jetpack_compose.R

@Composable
fun LottieAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    var isPlaying by remember { mutableStateOf(true) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
    )

    LaunchedEffect(key1 = progress) {
        if(progress == 0f) {
            isPlaying = true
        }
        if(progress == 1f) {
            isPlaying = false
        }
    }

    Column {
        com.airbnb.lottie.compose.LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            progress = { progress }, // one time animation
//            iterations = LottieConstants.IterateForever, // infinite animation
        )
        Button(
            onClick = {
                isPlaying = true
            }
        ) {
            Text(text = "Play")
        }
    }
}