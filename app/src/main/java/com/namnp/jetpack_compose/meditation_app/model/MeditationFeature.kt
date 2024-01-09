package com.namnp.jetpack_compose.meditation_app.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class MeditationFeature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
