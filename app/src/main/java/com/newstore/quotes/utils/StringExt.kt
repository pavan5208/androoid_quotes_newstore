package com.newstore.quotes.utils

import android.content.Context
import com.newstore.quotes.R


fun String?.getIfNullOrBlank(context: Context): String {
    return if (this == null || this.isBlank())
        context.getString(R.string.na)
    else this
}

fun String?.getStringWithAppend(string: String,context:Context): String {
    return string + this.getIfNullOrBlank(context)
}

fun String.Companion.newLine() = "\n"
