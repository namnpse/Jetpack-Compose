package com.namnp.jetpack_compose.practice.effect_handler.launched_effect

import android.util.Log
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LaunchedEffectCompose(counter: Int) {

    val viewModel = viewModel<LaunchedEffectViewModel>()

    var text by remember {
        mutableStateOf("")
    }

    // whenever text changes, block in LaunchedEffect would be canceled and relaunched
    // cancels the previously running coroutine and launches a new one with the new suspend function
    // also cancels the launched coroutine when it leaves the Composition itself
    val snackbarCount = viewModel.snackbarCount.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = snackbarCount.value) { // can have key2, key3,..
        Log.d("launched-effect", "displaying launched effect for count ${snackbarCount.value}")
        try {
//            snackbarHostState.showSnackbar("LaunchedEffect snackbar", "Count: ${snackbarCount.value}")
        } catch (e: Exception) {
            Log.d("launched-effect", "launched Effect coroutine cancelled exception $e")
        }
    }

    /*
        D/launched-effect: displaying launched effect for count 1
        D/launched-effect: launched Effect coroutine cancelled exception kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@28abbfe
        D/launched-effect: displaying launched effect for count 2
        D/launched-effect: launched Effect coroutine cancelled exception kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@14b5985
        D/launched-effect: displaying launched effect for count 3
        */

    LaunchedEffect(key1 = text) { // can have key2, key3,..
        // coroutine scope inside LaunchedEffect, can launch coroutine or use suspend fun
        delay(2000L)
        println("Text: $text")
    }

    // collecting a flow is considered side effect -> use LaunchedEffect
    LaunchedEffect(key1 = true) { // hardcode true -> run only once
        viewModel.sharedFlow.collect { event ->
            when (event) {
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
    Button(onClick = { // not in Compose scope, trigger later time when user clicks a button
        // can call coroutines when not in the Composition (i.e launch coroutine out of the scope of Composable in during user actions)
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