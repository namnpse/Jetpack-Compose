package com.namnp.jetpack_compose.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namnp.jetpack_compose.ui.theme.fontFamily

@Composable
fun CircularProgressBarBuilder() {
    CircularProgressBarCompose(
        percentage = 0.9f,
        number = 100,
    )
}

@Composable
fun CircularProgressBarCompose(
    percentage: Float,
    number: Int = 100,
    fontSize: TextUnit = 26.sp,
    radius: Dp = 60.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    duration: Int = 1000,
    delay: Int = 100
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        label = "circular_progress",
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
        )
    )

    // use to trigger animation
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * 2f),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = color,
                startAngle = -90f, // start at top
                sweepAngle = 360 * currentPercentage,
                useCenter = false, // disable line from progress bar to center point
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage * number).toInt().toString(),
            color = Color.Black,
            fontFamily = fontFamily,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )
    }

}