package com.newstore.quotes.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold =
        5 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var current_page = 1
    private var MAX_ITEMS_PER_PAGE = 25

    fun setMaxDataSizePerPage(dataSize:Int){
        MAX_ITEMS_PER_PAGE = dataSize
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = mLinearLayoutManager.itemCount
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
            val need_to_load = totalItemCount >= MAX_ITEMS_PER_PAGE * current_page

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && need_to_load) {
                // End has been reached

                // Do something
                current_page++

                onLoadMore(current_page)

                loading = true
            }
        }
    }

     abstract fun onLoadMore(current_page: Int)

     fun resetRecylerView() {
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
        current_page = 1
        previousTotal = 0 // The total number of items in the dataset after the last load
        loading = true // True if we are still waiting for the last set of data to load.
    }
}
