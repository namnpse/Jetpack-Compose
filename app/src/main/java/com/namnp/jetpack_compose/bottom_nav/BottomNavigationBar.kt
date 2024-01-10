@file:OptIn(ExperimentalMaterial3Api::class)

package com.namnp.jetpack_compose.bottom_nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavModel(
                        title = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavModel(
                        title = "Chat",
                        route = "chat",
                        icon = Icons.Default.MailOutline,
                        badgeCount = 23
                    ),
                    BottomNavModel(
                        title = "Settings",
                        route = "settings",
                        icon = Icons.Default.Notifications,
//                        badgeCount = 214,
                        showDot = true,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Navigation(navController = navController)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            NotificationScreen()
        }
    }
}

//@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavModel>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavModel) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState() // state, changes when navigate screens
    /*    NavigationRail {
            // for tablets, foldable devices, landscape mode
        }*/
    val selectedItemIndex by rememberSaveable { // handle config changes
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
        tonalElevation = 5.dp
    ) {
        items.forEachIndexed { index, item ->
//            val selected = item.route == backStackEntry.value?.destination?.route
            val selected = selectedItemIndex == index
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    selectedTextColor = Color.Green,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if(item.badgeCount > 0) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if(item.showDot) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}