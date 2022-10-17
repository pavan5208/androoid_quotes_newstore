package com.newstore.quotes.domain.models

class QuotesListRequestParams(
    var searchKeyWord: String? = null,
    var type: String? = null,
    var pageNo: Int = 1
)