package com.namnp.jetpack_compose.animation

import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.namnp.jetpack_compose.R

@Composable
fun MarqueeEffect() {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .background(Color.Green)
                .padding(vertical = 12.dp)
                .basicMarquee(
                    initialDelayMillis = 0,
                    delayMillis = 1000,
                    iterations = Int.MAX_VALUE,
                    velocity = 100.dp
                ),
            color = Color.White,
            text = "MARQUEE: Moving Animation with Marquee Effect and Jetpack Compose. This is the demonstration with Text",
        )
        Row(
            modifier = Modifier
                .basicMarquee(
                    animationMode = MarqueeAnimationMode.WhileFocused,
                    velocity = 100.dp
                )
                // using in the right order
                .focusRequester(focusRequester)
                .focusable()
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.train),
                contentDescription = "Train",
            )
            repeat(20) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.train_wagon),
                    contentDescription = "Train",
                )
            }
        }
        Button(onClick = {
            focusRequester.requestFocus()
        }) {
            Text(text = "Start Marquee")
        }
    }
}