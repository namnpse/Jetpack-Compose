@file:OptIn(ExperimentalMaterial3Api::class)

package com.namnp.jetpack_compose.practice.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            Column {
                for (userProfile in userProfiles)
                    ProfileCard(userProfile = userProfile)
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
        Image(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = drawableId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
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