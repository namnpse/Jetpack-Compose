@file:OptIn(ExperimentalMaterial3Api::class)

package com.namnp.jetpack_compose.practice.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.namnp.jetpack_compose.practice.model.UserProfile
import com.namnp.jetpack_compose.practice.model.userProfileList
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme
import com.namnp.jetpack_compose.ui.theme.lightGreen

const val USER_LIST_ROUTE = "user_list"
const val USER_DETAILS_ROUTE = "user_details"
const val USER_ID = "user_id"

class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = USER_LIST_ROUTE) {
        composable(USER_LIST_ROUTE) {
            UserListScreen(userProfiles, navController)
        }
        composable(
            route= "$USER_LIST_ROUTE/{$USER_ID}",
            arguments = listOf(
                navArgument(USER_ID) {
                    type = NavType.IntType
                }
            ),
        ) { navBackStackEntry ->
            UserProfileDetailsScreen(
                navController = navController,
                userId = navBackStackEntry.arguments!!.getInt(USER_ID),
            )
        }
    }
}

@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(title) }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
//            ProfilePicture(userProfile.url, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }

    }
}

@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(
            title = "Users list",
            icon = Icons.Default.Home
        ) { }
    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            LazyColumn {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) {
                        navController?.navigate("$USER_DETAILS_ROUTE/${userProfile.userId}")
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.userId }
    Scaffold(topBar = {
        AppBar(
            title = "User profile details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
//                ProfilePicture(userProfile.drawableId, userProfile.status, 240.dp)
//                ProfilePicture(userProfile.url, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus)
                MaterialTheme.colorScheme.lightGreen
            else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = pictureUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        transformations(CircleCropTransformation())
                    }).build()
            ),
            modifier = Modifier.size(72.dp),
            contentDescription = "Profile picture description",
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = if (onlineStatus)
                "Active now"
            else "Offline",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    JetpackComposeTheme {
        UserProfileDetailsScreen(userId = 0, null)
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    JetpackComposeTheme {
        UserListScreen(userProfiles = userProfileList, null)
    }
}