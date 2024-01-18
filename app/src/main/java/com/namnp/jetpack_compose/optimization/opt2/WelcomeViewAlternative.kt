package com.namnp.jetpack_compose.optimization.opt2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WelcomeViewAlternative(
    username: String, // should pass primitive type instead of ExternalUser object -> to avoid recomposition
    // if ExternalUser object has many properties -> use Mapper class to convert to internal class User
) {
    Text(text = "Welcome $username!")
}