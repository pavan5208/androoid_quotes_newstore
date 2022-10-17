package com.newstore.quotes.base

interface Mapper<From,To>{
    fun map(from: From):To
}