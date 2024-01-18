package com.namnp.jetpack_compose.optimization.opt2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.namnp.external.ExternalUser

@Composable
fun WelcomeView(
    user: ExternalUser // should not pass user from external module,
    // since it's marked as Unstable -> recompose
) {
    Text(text = "Welcome ${user.username}!")
}