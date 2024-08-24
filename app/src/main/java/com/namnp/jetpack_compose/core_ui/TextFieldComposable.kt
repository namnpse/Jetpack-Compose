package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldComposable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        // basic text fields: by default, don't have background color, leading icon, trailing icon, etc.
        var text by remember { mutableStateOf("Initial text") }
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
        )
        TextField(
            value = "Prefilled value that cannot be modified",
            onValueChange = {},
            enabled = false,
            readOnly = true, // cannot be modified, usually used for prefilled values, only copy value to clipboard
            label = {
                Text("Email")
            },
            singleLine = true, // only one line of text
            maxLines = 2, // max number of lines
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email icon")
                }
            },
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Check icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go // show in the keyboard: ImeAction.Done, ImeAction.Search, etc.
            )
        )

        // similar to TextField, but has border stroke, no background color
        OutlinedTextField(
            value = "Outlined text field",
            onValueChange = {},
            label = {
                Text("Password")
            },
            maxLines = 2, // max number of lines
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email icon")
                }
            },
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Check icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done // show in the keyboard: ImeAction.Done, ImeAction.Search, etc.
            )
        )

    }

}