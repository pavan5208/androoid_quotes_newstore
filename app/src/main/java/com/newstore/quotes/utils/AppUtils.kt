package com.newstore.quotes.utils

import android.app.Activity
import android.content.Context
import androidx.core.app.ShareCompat
import com.newstore.quotes.R
import com.newstore.quotes.domain.models.QuoteData
import java.lang.ref.WeakReference

object AppUtils {
    fun getErrorMsg(context: Context, msg: String?): String {
        return if (!context.isNetworkAvailable()) {
            context.getStringResource(R.string.network_error)
        } else if (!msg.isNullOrBlank()) {
            msg
        } else {
            context.getStringResource(R.string.something_wrong)
        }
    }

    fun shareQuote(message: String, context: WeakReference<Activity>) {
        context.get()?.let {
            val intent = ShareCompat.IntentBuilder(it)
                .setType("text/plain")
                .setText(message)
                .intent
            if (intent.resolveActivity(it.packageManager) != null) {
                it.startActivity(intent)
            }
        }

    }

    fun getQuoteDetailString(quoteData: QuoteData, context: Context): String {
        return (quoteData.quote.toString() + String.newLine() + String.newLine()
                + quoteData.getTagsString()
            .getStringWithAppend(
                context.getString(R.string.tags), context
            ) + String.newLine() + String.newLine()
                + quoteData.quoteFavoritesCount?.toString()
            .getStringWithAppend(
                context.getString(R.string.fav_count),
                context
            ) + String.newLine() + String.newLine()
                + quoteData.quoteUpVotesCount?.toString()
            .getStringWithAppend(
                context.getString(R.string.upvote_count),
                context
            ) + String.newLine() + String.newLine()
                + quoteData.quoteDownVotesCount?.toString()
            .getStringWithAppend(
                context.getString(R.string.down_vote_count),
                context
            ) + String.newLine() + String.newLine()
                + quoteData.quoteAuthor.getStringWithAppend(
            context.getString(R.string.author),
            context
        ))
    }

    enum class QuoteActions{
        ITEM_CLICK,SHARE_CLICK, COPY_CLICK
    }
}