package com.renanparis.chuckjokes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.renanparis.chuckjokes.usecase.RandomJokeUseCase

class RandomJokeViewModel(private val useCase: RandomJokeUseCase): ViewModel() {

    fun getRandomJoke(category: String) = useCase.getRandomJoke(category)


}
