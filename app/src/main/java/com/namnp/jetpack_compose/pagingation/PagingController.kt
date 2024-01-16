package com.namnp.jetpack_compose.pagingation

interface Paginator<Key, Item> {

    suspend fun loadNextItems()

    fun refresh()
}