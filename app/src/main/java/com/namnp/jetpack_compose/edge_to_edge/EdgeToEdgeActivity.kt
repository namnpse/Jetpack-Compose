package com.namnp.jetpack_compose.edge_to_edge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.jetpack_compose.R
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

class EdgeToEdgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                val systemUiController = rememberSystemUiController()
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
                }
               Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.github_worth),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .background(color = Color.Black)
                            .statusBarsPadding()
                            .navigationBarsPadding()

                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) {
                            items(30) {
                                Text(
                                    text = "${(it + 1)}. ENABLED EDGE TO EDGE",
                                    color = Color.White,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}