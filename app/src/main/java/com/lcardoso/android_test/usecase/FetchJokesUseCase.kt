package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.data.JokesRepositoryImp

class FetchJokesUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute(params: Param) = repository.fetchJokes(params.category)

    data class Param(
        val category: String
    )
}