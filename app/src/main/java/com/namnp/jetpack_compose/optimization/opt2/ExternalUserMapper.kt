package com.namnp.jetpack_compose.optimization.opt2

import com.namnp.external.ExternalUser

data class User(
    val id: String,
    val email: String,
    val username: String
)

fun ExternalUser.toUser(): User {
    return User(
        id = id,
        email = email,
        username = username
    )
}

fun User.toExternalUser(): ExternalUser {
    return ExternalUser(
        id = id,
        email = email,
        username = username
    )
}