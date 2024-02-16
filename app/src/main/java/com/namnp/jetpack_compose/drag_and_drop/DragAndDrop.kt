package com.namnp.jetpack_compose.drag_and_drop

import android.content.ClipData
import android.view.View
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDrop(
    modifier: Modifier = Modifier,
) {
    val label = remember { "Drag me" }
    Box(
        modifier = modifier
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        startTransfer(
                            DragAndDropTransferData(
                                clipData = ClipData.newPlainText(label, label),
                                flags = View.DRAG_FLAG_GLOBAL,
                            )
                        )
                    }
                )
            }
            .border(
                border = BorderStroke(
                    width = 4.dp,
                    brush = Brush.linearGradient(listOf(Color.Magenta, Color.Magenta))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = label
        )
    }
}