package com.namnp.jetpack_compose.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

@Composable
fun AnimatedCounterBuilder() {
    JetpackComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var count by remember {
                mutableIntStateOf(0)
            }
            AnimatedCounter(
                count = count,
                style = MaterialTheme.typography.headlineMedium
            )
            Button(onClick = { count++ }) {
                Text(text = "Increment")
            }
        }
    }
}

@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
   /* var oldCount by remember {
        mutableIntStateOf(count)
    }
    SideEffect {
        oldCount = count
    }*/
    Row(modifier = modifier) {
        val countString = count.toString()
//        val oldCountString = oldCount.toString()
        for (i in countString.indices) {
            /*val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if (oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }*/
            AnimatedContent(
                targetState = countString[i],
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }, label = "content"
            ) { character ->
                Text(
                    text = character.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}