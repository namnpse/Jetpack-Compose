package com.namnp.jetpack_compose.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavModel(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0,
    val showDot: Boolean = false,
)