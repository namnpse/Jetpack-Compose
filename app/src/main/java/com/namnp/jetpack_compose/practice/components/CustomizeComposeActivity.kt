package com.namnp.jetpack_compose.practice.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

class CustomizeComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                ImageCardBuilder()
            }
        }
    }
}