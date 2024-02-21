package com.namnp.jetpack_compose.kotlin.utils

fun allNullOrEmpty(vararg strings: String?): Boolean =
    strings.all { it.isNullOrEmpty() }

fun anyNullOrEmpty(vararg strings: String?): Boolean =
    strings.any { it.isNullOrEmpty() }

// Usage
fun main() {
    val name = ""
    val email = ""
    val phone: String? = null
    val address: String? = null
    val city = "city"
    // avoid doing this:
    if (name.isNullOrEmpty() || email.isNullOrEmpty() || phone.isNullOrEmpty()
        || address.isNullOrEmpty() || city.isNullOrEmpty()) {

    }
    // SHOULD use:
    if(anyNullOrEmpty(name, email, phone, address, city)) {

    }
}