package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle

@Composable
fun SuperscriptAndSubscriptText(modifier: Modifier = Modifier) {
    Column {
        SuperscriptText(
            normalText = "Normal text",
            superscriptText = "Superscript text"
        )
        SubscriptText(
            normalText = "Normal text",
            subscriptText = "Subscript text"
        )
    }
}

@Composable
fun SuperscriptText(
    modifier: Modifier = Modifier,
    normalText: String,
    superscriptText: String,
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            ) {
                append(normalText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Superscript,
                )
            ) {
                append(superscriptText)
            }
        }
    )
}

@Composable
fun SubscriptText(
    modifier: Modifier = Modifier,
    normalText: String,
    subscriptText: String,
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            ) {
                append(normalText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Subscript,
                )
            ) {
                append(subscriptText)
            }
        }
    )
}