package com.namnp.jetpack_compose.practice.effect_handler.launched_effect

import androidx.compose.animation.core.Animatable
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LaunchedEffectCompose(counter: Int) {

    var text by remember {
        mutableStateOf("")
    }

    // whenever text changes, block in LaunchedEffect would be canceled and relaunched
    LaunchedEffect(key1 = text) {
        // coroutine scope inside LaunchedEffect, can launch coroutine or use suspend fun
        delay(2000L)
        println("Text: $text")
    }

    val viewModel = viewModel<LaunchedEffectViewModel>()
    // collecting a flow is considered side effect -> use LaunchedEffect
    LaunchedEffect(key1 = true) { // hardcode true -> run only once
        viewModel.sharedFlow.collect { event ->
            when(event) {
                is ScreenEvent.ShowSnackbar -> {

                }
                is ScreenEvent.Navigate -> {

                }
            }
        }
    }

    // Starting animation using LaunchedEffect
    val animation = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animation.animateTo(counter.toFloat())
    }

    // rememberCoroutinesScope
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = {
        coroutineScope.launch { // use in Callback, cannot use Compose function
            delay(1000L)
            println("Hello Namnpse")
        }
    }) {

    }
}

@Composable
fun RememberUpdatedStateCompose(
    onFinish: (() -> Unit)?
) {

    // e.g: RememberUpdatedStateCompose is called many places with different onFinish()
    // -> update updatedOnFinish when having different onFinish()
    val updatedOnFinish by rememberUpdatedState(newValue = onFinish)

    // usecase: the splash screen
    LaunchedEffect(key1 = true) {
        delay(3000L)
        updatedOnFinish?.invoke()
    }
}