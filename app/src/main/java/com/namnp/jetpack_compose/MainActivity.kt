package com.namnp.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.namnp.jetpack_compose.practice.components.MainScreen
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme
import com.namnp.jetpack_compose.util.LifecycleEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                MainScreen()
            }
            initiateLifecycleObserver()
        }
    }

    private fun initiateLifecycleObserver() {
        val lifeCycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> lifecycleCallBack(LifecycleEvent.onResume)
                Lifecycle.Event.ON_PAUSE -> lifecycleCallBack(LifecycleEvent.onPause)
                Lifecycle.Event.ON_CREATE -> lifecycleCallBack(LifecycleEvent.onCreate)
                Lifecycle.Event.ON_STOP -> lifecycleCallBack(LifecycleEvent.onStop)
                Lifecycle.Event.ON_DESTROY -> lifecycleCallBack(LifecycleEvent.onDestroy)
                Lifecycle.Event.ON_START -> lifecycleCallBack(LifecycleEvent.onStart)
                Lifecycle.Event.ON_ANY -> lifecycleCallBack(LifecycleEvent.onAny)
            }
        }
        lifecycle.addObserver(lifeCycleObserver)
    }
}

fun lifecycleCallBack(event: LifecycleEvent) {
    println(event.name)
}

@Composable
fun GreetingText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingButton(name: String, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        onClick = {

        },
    ) {
        Text(
            text = "Hello $name!"
        )
    }
}

// multiple preview at a time
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingText("Android 22")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingText("Android 2023")
        }
    }
}