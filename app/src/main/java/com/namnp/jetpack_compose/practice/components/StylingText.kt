package com.namnp.jetpack_compose.practice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.namnp.jetpack_compose.ui.theme.fontFamily

@Composable
fun StylingTextBuilder() {

    StylingText()
}

@Composable
fun StylingText() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
    ) {
        Text(
//            text = "Namnpse",
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 12.sp
                    )
                ) {
                    append("N") // use customized style with Green color
                }
                append("amnpse") // use default style as below (with Black color)
            },
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
        )
    }
}