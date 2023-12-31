package com.namnp.jetpack_compose.practice.ui

import com.namnp.jetpack_compose.R

data class UserProfile(val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf(
    UserProfile(name = "Nam", status = true, R.drawable.ic_launcher_background),
    UserProfile(name = "Bryan", status = false, R.drawable.ic_launcher_foreground),
)