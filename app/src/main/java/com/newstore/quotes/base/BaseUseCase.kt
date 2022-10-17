package com.newstore.quotes.base


interface BaseUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}