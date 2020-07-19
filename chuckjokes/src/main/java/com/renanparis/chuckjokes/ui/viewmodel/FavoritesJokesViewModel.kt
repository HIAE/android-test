package com.renanparis.chuckjokes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.usecase.FavoritesJokeUseCase

class FavoritesJokesViewModel(private val useCase: FavoritesJokeUseCase): ViewModel() {

    fun getFavoritesJokes() = useCase.getFavoritesJoke()
    fun removeFavoriteJoke(joke: Joke) = useCase.removeFavoriteJoke(joke)
}