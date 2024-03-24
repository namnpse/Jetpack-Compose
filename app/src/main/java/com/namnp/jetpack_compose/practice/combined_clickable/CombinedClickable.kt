package com.namnp.jetpack_compose.practice.combined_clickable

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickable() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .combinedClickable(
                enabled = true,
                onClick = {
                    Toast.makeText(context, "onClick", Toast.LENGTH_LONG).show()
                },
                onLongClick = {
                    Toast.makeText(context, "onLongClick", Toast.LENGTH_LONG).show()
                },
                onDoubleClick = {
                    Toast.makeText(context, "onDoubleClick", Toast.LENGTH_LONG).show()
                }
            )
    ) {
        Text(text = "Combined Click")
    }
}