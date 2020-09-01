package com.lcardoso.android_test.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.android_test.BaseViewModel
import com.lcardoso.android_test.data.database.JokeEntity
import com.lcardoso.android_test.usecase.FetchFavoriteJokeUseCase
import com.lcardoso.android_test.usecase.RemoveFavoriteJokeUseCase

class FavoritesViewModel(
    private val fetchFavoritesUseCase: FetchFavoriteJokeUseCase,
    private val removeFavoriteJokeUseCase: RemoveFavoriteJokeUseCase
) : BaseViewModel() {

    val favoriteJokesLiveData: LiveData<List<JokeEntity>> get() = _favoriteJokesLiveData
    private val _favoriteJokesLiveData = MutableLiveData<List<JokeEntity>>()

    fun fetchFavoriteJokes() {
        fetchFavoritesUseCase.execute().subscribe(
            { jokes ->
                _favoriteJokesLiveData.value = jokes
            }, { e ->
                Log.d("lero", e.message)
            }
        ).let { disposables.add(it) }
    }

    fun removeFavoriteJoke(joke: JokeEntity) {
        removeFavoriteJokeUseCase.execute(
            RemoveFavoriteJokeUseCase.Param(
                id = joke.id,
                category = joke.category,
                joke = joke.joke

            )
        ).subscribe(
            {}, { e ->
                Log.d("lero", e.message)
            }
        ).let { disposables.add(it) }
    }
}