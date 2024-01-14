package com.namnp.jetpack_compose.nav_destination

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class NavDestinationActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable(
                        route = "profile/{name}/{userId}/{timestamp}",
                        arguments = listOf(
                            navArgument("name") {
                                type = NavType.StringType
                            },
                            navArgument("userId") {
                                type = NavType.StringType
                            },
                            navArgument("timestamp") {
                                type = NavType.LongType
                            },
                        )
                    ) {
                        val name = it.arguments?.getString("name")!!
                        val userId = it.arguments?.getString("userId")!!
                        val timestamp = it.arguments?.getLong("timestamp")!!

                        ProfileScreen(
                            navController = navController,
                            name = name,
                            userId = userId,
                            created = timestamp
                        )
                    }
                    composable("post/{showOnlyPostsByUser}", arguments = listOf(
                        navArgument("showOnlyPostsByUser") {
                            type = NavType.BoolType
                            defaultValue = false
                        }
                    )) {
                        val showOnlyPostsByUser =
                            it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
                        PostScreen(showOnlyPostsByUser)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navController.navigate("profile/namnpse/userid/123456789")
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long
) {
    val user = remember {
        User(
            name = name,
            id = userId,
            created = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}