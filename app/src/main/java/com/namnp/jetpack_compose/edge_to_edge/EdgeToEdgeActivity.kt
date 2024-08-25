package com.namnp.jetpack_compose.edge_to_edge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.namnp.jetpack_compose.R
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

class EdgeToEdgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // C1: enable fullscreen mode including status bar and navigation bar
        enableEdgeToEdge(
            // dark/light theme to set color for status bar contrast (icons, datetime,...) and navigation bar
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
            /*    val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setNavigationBarColor(
                        // setColor Here
                        color = Color.Transparent,
                        darkIcons = false
                    )
                    // C2: manually setStatusBarColor in each screen
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                    )
                }*/
               Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.img_avatar),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
//                    Column(
//                        modifier = Modifier
//                            .background(color = Color.Black)
//                            .statusBarsPadding()
//                            .navigationBarsPadding()
//
//                    ) {
//                        LazyColumn(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .weight(1f)
//                        ) {
//                            items(30) {
//                                Text(
//                                    text = "${(it + 1)}. ENABLED EDGE TO EDGE",
//                                    color = Color.White,
//                                    modifier = Modifier.padding(10.dp)
//                                )
//                            }
//                        }
//                    }
                }
            }
        }
    }
}