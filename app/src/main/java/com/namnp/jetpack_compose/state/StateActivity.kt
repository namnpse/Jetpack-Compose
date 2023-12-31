package com.namnp.jetpack_compose.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class CoreUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateScreen()
        }
    }
}

@Composable
fun StateScreen() {
    val nameList = remember { mutableStateListOf<String>("Nam", "Bryan") }
    val newName = remember { mutableStateOf("") }
    GreetingList(
        nameList,
        onButtonAddClick = {
            nameList.add(newName.value)
        },
        newName.value,
        onValueChange = { name ->
            newName.value = name
        }
    )
}

@Composable
fun GreetingList(
    nameList: List<String>,
    onButtonAddClick: () -> Unit,
    newName: String,
    onValueChange: (String) -> Unit,
) {

    Column {
        for (name in nameList) {
            Text(text = "Hello $name")
        }
        TextField(
            value = newName,
            onValueChange = onValueChange,
        )
        Button(onClick = onButtonAddClick) {
            Text(text = "Add name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateScreenPreview() {
    StateScreen()
}