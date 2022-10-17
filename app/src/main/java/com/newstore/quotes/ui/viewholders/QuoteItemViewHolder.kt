package com.newstore.quotes.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.newstore.quotes.R
import com.newstore.quotes.databinding.ItemQuoteBinding
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.utils.AppUtils
import com.newstore.quotes.utils.getStringWithAppend

class QuoteItemViewHolder(
    private val quoteBinding: ItemQuoteBinding,
    private val onCLick: (AppUtils.QuoteActions, QuoteData) -> Unit
) :
    RecyclerView.ViewHolder(quoteBinding.root) {
    private lateinit var quoteData: QuoteData
    val context = quoteBinding.root.context

    init {
        quoteBinding.layoutQuoteParent.setOnClickListener {
            onCLick.invoke(AppUtils.QuoteActions.ITEM_CLICK, quoteData)
        }
        quoteBinding.imCopy.setOnClickListener {
            onCLick.invoke(AppUtils.QuoteActions.COPY_CLICK, quoteData)
        }
        quoteBinding.imShare.setOnClickListener {
            onCLick.invoke(AppUtils.QuoteActions.SHARE_CLICK, quoteData)
        }
    }

    fun bindData(quoteData: QuoteData) {
        this.quoteData = quoteData
        quoteBinding.apply {
            txtQuote.text = quoteData.quote.orEmpty()
            txtTags.text = quoteData.getTagsString().getStringWithAppend(
                context.getString(R.string.tags), context
            )
            txtAuthor.text = quoteData.quoteAuthor?.getStringWithAppend(
                context.getString(R.string.author), context
            )
        }
    }
}