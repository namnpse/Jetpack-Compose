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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.namnp.jetpack_compose.nav_destination.destinations.PostScreenDestination
import com.namnp.jetpack_compose.nav_destination.destinations.ProfileScreenDestination
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime

class NavDestinationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
//            navController.navigate("profile/namnpse/id1234/1705203882")
            navigator.navigate(
                ProfileScreenDestination(
                    User( // use for passing object User()
                        name = "namnpse",
                        id = "id1234",
                        created = LocalDateTime.now() // System.currentTimeMillis()
                    ),
  /*                  ProfileScreenDestination.NavArgs( // use for 3 separate arguments (passing primitive params)
                        name = "namnpse",
                        userId = "id1234",
                        created = LocalDateTime.now() // System.currentTimeMillis()
                    )*/
                )
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination(
//    navArgsDelegate = User::class
    /*comment out if get the arguments in Composable,
    but if get arguments in ViewModel, need to declare
    */
)
@Composable
fun ProfileScreen(
    /*    navController: NavController,
        name: String,
        userId: String,
        created: Long*/
    navigator: DestinationsNavigator,
    user: User
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
//            navController.navigate("post/true")
            navigator.navigate(PostScreenDestination(showOnlyPostsByUser = true))
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Destination
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