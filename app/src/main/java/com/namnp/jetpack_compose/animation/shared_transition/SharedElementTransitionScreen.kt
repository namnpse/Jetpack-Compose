@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.namnp.jetpack_compose.animation.shared_transition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme
import kotlinx.serialization.Serializable

class SharedElementTransitionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                TypeSafeSharedElementTransitionScreen()
            }
        }
    }
}

// using type-safe Navigation
@Composable
fun TypeSafeSharedElementTransitionScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ListTrainScreen
            ) {
                composable<ListTrainScreen> {
                    ListTrainScreen(
                        onItemClick = { resId, text ->
                            navController.navigate(
                                DetailTrainScreen(
                                    resId = resId,
                                    text = text,
                                )
                            )
                        },
                        animatedVisibilityScope = this
                    )
                }
                composable<DetailTrainScreen> {
                    val arg = it.toRoute<DetailTrainScreen>()
                    DetailTrainScreen(
                        resId = arg.resId,
                        text = arg.text,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}

@Composable
fun SharedElementTransitionScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "list"
            ) {
                composable("list") {
                    ListTrainScreen(
                        onItemClick = { resId, text ->
                            navController.navigate("detail/$resId/$text")
                        },
                        animatedVisibilityScope = this
                    )
                }
                composable(
                    route = "detail/{resId}/{text}",
                    arguments = listOf(
                        navArgument("resId") {
                            type = NavType.IntType
                        },
                        navArgument("text") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val resId = it.arguments?.getInt("resId") ?: 0
                    val text = it.arguments?.getString("text") ?: ""
                    DetailTrainScreen(
                        resId = resId,
                        text = text,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}

@Serializable
object ListTrainScreen

@Serializable
data class DetailTrainScreen(
    @DrawableRes val resId: Int,
    val text: String
)

