package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.api.defaultSchedulers
import com.lcardoso.android_test.data.JokesRepositoryImp

class IsFavoriteJokeUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute(params: Param) = repository.isFavoriteJoke(id = params.id).defaultSchedulers()

    data class Param(
        val id: String
    )
}