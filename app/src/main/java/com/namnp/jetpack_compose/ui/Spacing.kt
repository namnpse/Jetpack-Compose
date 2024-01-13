package com.namnp.jetpack_compose.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

// take Spacing to MaterialTheme
// useful when we want to define other Spacing objects for TabletSpacing, DesktopSpacing
// or want to refactor this Spacing in the future,
// just need to update Spacing values in handle in Theme class
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current