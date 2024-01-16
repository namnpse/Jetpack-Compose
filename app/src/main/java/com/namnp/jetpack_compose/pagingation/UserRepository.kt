package com.namnp.jetpack_compose.pagingation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserRepository {

    private val userRemoteDataSource = (1..100).map { index ->
        User(
            id = index,
            name = "Namnpse $index",
            address = "US $index"
        )
    }


    suspend fun getUsers(page: Int, pageSize: Int): Result<List<User>> =
        withContext(Dispatchers.IO) {
            delay(2000L)
            val startIndex = page * pageSize
            if (startIndex + pageSize <= userRemoteDataSource.size) {
                Result.success(
                    userRemoteDataSource.slice(startIndex until startIndex + pageSize)
                )
            } else {
                Result.success(emptyList())
            }
        }
}