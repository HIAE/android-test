package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.data.JokesRepositoryImp

class FetchPreviousJokeUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute(params: Param) = repository.fetchPreviousJoke(params.jokeId)

    data class Param(
        val jokeId: String
    )
}