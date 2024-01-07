@file:OptIn(ExperimentalComposeUiApi::class)

package com.namnp.jetpack_compose.animation

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.namnp.jetpack_compose.R
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun DraggableVolumeBuilder() {

    var volume by remember {
        mutableFloatStateOf(0f)
    }
    val numberOfBars = 20

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            DraggableVolume(
                modifier = Modifier.size(100.dp),
                onVolumeChange = {
                    volume = it
                },
            )
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (numberOfBars * volume).roundToInt(),
                numberOfBars = numberOfBars
            )
        }
    }
}

@Composable
fun DraggableVolume(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onVolumeChange: (Float) -> Unit,
) {

    var rotation by remember {
        mutableFloatStateOf(0f)
    }

    var xCoordinate by remember {
        mutableFloatStateOf(0f)
    }

    var yCoordinate by remember {
        mutableFloatStateOf(0f)
    }

    var centerX by remember {
        mutableFloatStateOf(0f)
    }

    var centerY by remember {
        mutableFloatStateOf(0f)
    }

    Image(
        painter = rememberAsyncImagePainter(R.drawable.music_volume),
        contentDescription = "music_volume",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                val windowBoundsSize = coordinates.boundsInWindow().size
                centerX = windowBoundsSize.width / 2f
                centerY = windowBoundsSize.height / 2f
            }
            .pointerInteropFilter { event ->// similar to onTouchListener
                xCoordinate = event.x
                yCoordinate = event.y
                val degree = -atan2(centerY - yCoordinate, centerX - xCoordinate)
                val angleInRadian = (degree * 180f / PI).toFloat()

                when (event.action) {
                    // Touch
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angleInRadian !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angleInRadian !in -180f..-limitingAngle) {
                                360f + angleInRadian
                            } else angleInRadian

                            rotation = fixedAngle

                            val percentage =
                                (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onVolumeChange(percentage)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    numberOfBars: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * numberOfBars)
        }
        Canvas(modifier = modifier) {
            for(i in 0 until numberOfBars) {
                drawRoundRect(
                    color = if(i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DraggableVolumePreview() {
    DraggableVolume(onVolumeChange = {})
}