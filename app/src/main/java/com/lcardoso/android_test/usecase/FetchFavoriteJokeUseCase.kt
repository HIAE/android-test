package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.api.defaultSchedulers
import com.lcardoso.android_test.data.JokesRepositoryImp

class FetchFavoriteJokeUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute() = repository.fetchFavoritesJokes().defaultSchedulers()
}