package com.namnp.jetpack_compose.practice.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ModifierOrder() {
    Text(
        "Namnpse",
        modifier = Modifier
            .border(width = 2.dp, color = Color.Green)
            .padding(20.dp)
            .border(width = 2.dp, color = Color.Blue)
            .padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ModifierOrderPreview() {
    ModifierOrder()
}