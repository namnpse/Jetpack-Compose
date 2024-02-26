package com.namnp.jetpack_compose.practice.string_res

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.namnp.jetpack_compose.util.SingleEventEffect

@Composable
fun StringResourceCompose() {
    val viewModel = viewModel<StringResourceViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.errors.collect { error ->
            println(error.asString(context)) // normal asString
        }
    }

    // C1:
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = viewModel.errors) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.errors.collect {
                Toast.makeText(context, it.asString(context), Toast.LENGTH_LONG).show()
            }
        }
    }

    // C2: use common fun
    SingleEventEffect(sideEffectFlow = viewModel.errors) {
        Toast.makeText(context, it.asString(context), Toast.LENGTH_LONG).show()
    }

    Scaffold {
        viewModel.logMessage.value?.let { value ->
            Text(text = "Example String Resource Usage${value.asString()}") // Compose asString
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = viewModel::onNameChange,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.validateInputs() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Validate")
            }
        }
    }
}
