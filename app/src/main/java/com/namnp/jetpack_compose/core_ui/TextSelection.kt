package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextSelection(modifier: Modifier = Modifier) {
    SelectionContainer(modifier = modifier) {
        Column {
            Text(text = "Selectable text 1")
            DisableSelection {
                Text(text = "Non-selectable text")
            }
            Text(text = "Selectable text 2")
        }
    }
}