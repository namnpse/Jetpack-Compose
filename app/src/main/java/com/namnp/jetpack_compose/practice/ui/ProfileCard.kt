@file:OptIn(ExperimentalMaterial3Api::class)

package com.namnp.jetpack_compose.practice.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    // https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
    Scaffold(topBar = { AppBar() }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            /*          it's required to use padding parameter,
                        passed into Scaffold content composable.
                        apply it to the topmost container/view in content
                        This is done to prevent layout problems,
                        for example, when the scaffold has a bottom bar, without the use of this padding part of your view will be under the bar.*/
        ) {
//            Column {
//                for (userProfile in userProfiles)
//                    ProfileCard(userProfile = userProfile)
//            }
            // use LazyColumn as RecyclerView
            LazyColumn {
                items(userProfiles) { user ->
                    ProfileCard(userProfile = user)
                }
            }

        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 12.dp),
            )
        },
        title = { Text("Messenger") }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
        ) {
            ProfileImage(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name)
        }
    }
}

@Composable
fun ProfileImage(drawableId: Int, onlineStatus: Boolean) {
    Card(
        shape = CircleShape,
        modifier = Modifier.padding(8.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
    ) {
        val url = "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
//        Image(
//            modifier = Modifier.size(72.dp),
//            painter = painterResource(id = drawableId),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//        )
        // Load Local drawable file
//        Image(
//            modifier = Modifier.size(72.dp),
//            painter = rememberAsyncImagePainter(model = drawableId),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//        )
//        https://coil-kt.github.io/coil/compose/

        AsyncImage(
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
//            model = url,
//            model = drawableId, // Load Local drawable file
            contentDescription = null,
        )


    }

}

@Composable
fun ProfileContent(name: String) {
    Text(
        name,
//        modifier = Modifier.padding(16.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    MainScreen()
}