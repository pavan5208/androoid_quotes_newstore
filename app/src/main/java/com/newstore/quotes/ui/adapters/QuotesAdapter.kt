package com.newstore.quotes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newstore.quotes.databinding.ItemQuoteBinding
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.ui.viewholders.QuoteItemViewHolder
import com.newstore.quotes.utils.AppUtils

class QuotesAdapter(private val onCLick: (AppUtils.QuoteActions, QuoteData) -> Unit) :
    RecyclerView.Adapter<QuoteItemViewHolder>() {
    private val quotes = mutableListOf<QuoteData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuoteItemViewHolder(
        ItemQuoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onCLick
    )

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bindData(quotes[position])
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    fun submitData(items: List<QuoteData>, pageNo: Int?) {
        if (pageNo == null || pageNo == 1) {
            this.quotes.clear()
            notifyDataSetChanged()
        }
        this.quotes.addAll(items)
        notifyItemRangeChanged(this.quotes.size - items.size, items.size)
    }
}