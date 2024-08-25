package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    text: String,
    textColor: Color = Color.White,
    gradient: Brush,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick() })
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(all = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textColor)
        }
    }
}

@Preview
@Composable
fun GradientButtonPreview(modifier: Modifier = Modifier) {
    Column {
        GradientButton(
            text = "Gradient Button",
            gradient = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF484BF1),
                    Color(0xFF673AB7)
                )
            ),
            onClick = {},
        )
        GradientButton(
            text = "Gradient Button",
            gradient = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF484BF1),
                    Color.Cyan
                )
            ),
            onClick = {}
        )
    }
}