package com.namnp.jetpack_compose.util

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Float.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}