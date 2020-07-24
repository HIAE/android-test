package com.lcardoso.android_test.data

import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.doRequest
import com.lcardoso.android_test.data.database.JokeDAO
import com.lcardoso.android_test.data.database.JokeEntity
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.data.model.JokeVO
import com.lcardoso.android_test.util.toVO
import io.reactivex.Single

class JokesRepositoryImp(
    private val api: ChuckNorrisAPI,
    private val jokeDAO: JokeDAO
) {

    fun fetchCategories(): Single<CategoriesVO> = doRequest {
        api.fetchCategories().map { categories -> categories.toVO() }
    }

    fun fetchJokes(category: String): Single<JokeVO> = doRequest {
        api.fetchJokes(category).map { jokeResponse ->
            JokeVO(
                category = jokeResponse.categories?.first(),
                id = jokeResponse.id,
                joke = jokeResponse.value
            )
        }
    }

    fun fetchPreviousJoke(jokeId: String): Single<JokeVO> = doRequest {
        api.fetchPreviousJokes(jokeId).map { jokeResponse ->
            JokeVO(
                category = jokeResponse.categories?.first(),
                id = jokeResponse.id,
                joke = jokeResponse.value
            )
        }
    }

    fun addFavoriteJoke(joke: JokeEntity) = jokeDAO.addFavoriteJoke(joke)

    fun fetchFavoritesJokes() = jokeDAO.fetchFavoriteJokes()

    fun removeFavoriteJoke(joke: JokeEntity) = jokeDAO.removeFavoriteJoke(joke)
}