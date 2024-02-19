package com.namnp.jetpack_compose.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringValue {

    data class DynamicString(val value: String):  StringValue()

    data object EmptyString: StringValue()

    class StringResource(
         @StringRes val resId: Int,
         vararg val args: Any
    ): StringValue()

    @Composable
    fun asString(): String {
        return when(this) {
            is EmptyString -> ""
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args) // in Compose, don't need to pass Context
        }
    }

    fun asString(context: Context?): String {
        return when(this) {
            is EmptyString -> ""
            is DynamicString -> value
            is StringResource -> context?.getString(resId, *args).orEmpty()
        }
    }
}