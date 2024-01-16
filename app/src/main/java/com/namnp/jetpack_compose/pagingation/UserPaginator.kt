package com.namnp.jetpack_compose.pagingation

class UserPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadingData: (Boolean) -> Unit,
    private inline val onRequestData: suspend (key: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onSuccess: (items: List<Item>, newKey: Key) -> Unit,
    private inline val onError: (Throwable?) -> Unit,
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return

        isMakingRequest = true

        onLoadingData(true)
        val result = onRequestData(currentKey)
        result.fold(
            onSuccess = { items ->
                val newKey = getNextKey(items) // it could be user id, last id, last user token in list items
                onSuccess(items, newKey)
            },
            onFailure = {
                onError(it)
            }
        )
        isMakingRequest = false
        onLoadingData(false)

/*        val items = result.getOrElse {
            onError(it)
            onLoadingData(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadingData(false)*/
    }

    override fun refresh() {
        currentKey = initialKey
    }
}