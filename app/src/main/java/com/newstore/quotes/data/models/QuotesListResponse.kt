package com.newstore.quotes.data.models

import com.google.gson.annotations.SerializedName

class QuotesListResponse(
    @SerializedName("page")
    val pageNo: Int?,
    @SerializedName("last_page")
    val isLastPage: Boolean?,
    val quotes: List<Quote>?,
    @SerializedName("error_code")
    var errorCode: Int? = null,
    @SerializedName("message")
    var errorMsg: String? = null
)

class Quote(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("favorite")
    val isFavorite: Boolean?,
    @SerializedName("author_permalink")
    val authorPermalink: String?,
    @SerializedName("body")
    val quoteBody: String?,
    @SerializedName("favorites_count")
    val favoritesCount: Int?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("upvotes_count")
    var upVoteCount: Int? = null,
    @SerializedName("downvotes_count")
    var downVoteCount: Int? = null,
    @SerializedName("error_code")
    var errorCode: Int? = null,
    @SerializedName("message")
    var errorMsg: String? = null
)
