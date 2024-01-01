package com.namnp.jetpack_compose.practice.model

import com.namnp.jetpack_compose.R

data class UserProfile(val userId: Int, val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf(
    UserProfile(1, name = "Nam", status = true, R.drawable.ic_launcher_background),
    UserProfile(2, name = "Bryan", status = false, R.drawable.ic_launcher_foreground),
)