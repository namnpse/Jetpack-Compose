package com.namnp.jetpack_compose.animation_lottie

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.namnp.jetpack_compose.R

@Composable
fun Loader() {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
//    LottieAnimation(composition)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier.size(80.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever, // infinite animation
//        progress = { progress }, // one time animation
    )
}