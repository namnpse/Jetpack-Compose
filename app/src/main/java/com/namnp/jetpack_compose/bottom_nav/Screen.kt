package com.namnp.jetpack_compose.bottom_nav

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Chat : Screen("chat")
    data object Notification : Screen("notification")
//    data class Chat(val route: String, val icon: ImageVector, val title: String) : Screen(route, icon, title)
//    data class Notification(val route: String, val icon: ImageVector, val title: String) : Screen(route, icon, title)
}