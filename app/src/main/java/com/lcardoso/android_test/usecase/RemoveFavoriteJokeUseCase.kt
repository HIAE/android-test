package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.api.defaultSchedulers
import com.lcardoso.android_test.data.JokesRepositoryImp
import com.lcardoso.android_test.data.database.JokeEntity

class RemoveFavoriteJokeUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute(params: Param) = repository.removeFavoriteJoke(
        JokeEntity(id = null, category = null, joke = null)
    ).defaultSchedulers()

    data class Param(
        val id: String,
        val category: String,
        val joke: String
    )
}