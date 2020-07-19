package com.renanparis.chuckjokes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.usecase.RandomJokeUseCase

class RandomJokeViewModel(private val useCase: RandomJokeUseCase): ViewModel() {

    fun getRandomJoke(category: String) = useCase.getRandomJoke(category)

    fun deleteJoke(joke: Joke) = useCase.deleteJoke(joke)

    fun saveJoke(joke: Joke) = useCase.saveJoke(joke)


}
