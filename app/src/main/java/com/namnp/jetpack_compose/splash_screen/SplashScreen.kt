package com.namnp.jetpack_compose.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.namnp.jetpack_compose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) { // run once when screen opens
        scale.animateTo(
            targetValue = 0.3f, // image quite big, 0.3f is okay, can adjust
            animationSpec = tween(
                durationMillis = 1000,
                easing = Easing {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screen.Main.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = rememberAsyncImagePainter(model = R.drawable.github_worth),
            contentDescription = "Logo",
        )
    }
}
