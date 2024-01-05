package com.namnp.jetpack_compose.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.namnp.jetpack_compose.R

val fontFamily = FontFamily(
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semi_bold, FontWeight.SemiBold),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_extra_light, FontWeight.ExtraLight),
    Font(R.font.lexend_extra_bold, FontWeight.ExtraBold),
    Font(R.font.lexend_black, FontWeight(950)), // customized
)