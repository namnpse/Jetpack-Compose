package com.namnp.jetpack_compose.util

import android.content.Context
import androidx.annotation.StringRes

sealed class StringValue {

    data class DynamicString(val value: String):  StringValue()

    data object EmptyString: StringValue()

    class StringResource(
         @StringRes val resId: Int,
         vararg val args: Any
    ): StringValue()

    fun asString(context: Context?): String {
        return when(this) {
            is EmptyString -> ""
            is DynamicString -> value
            is StringResource -> context?.getString(resId, *args).orEmpty()
        }
    }
}