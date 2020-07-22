package com.renanparis.chuckjokes.usecase

import androidx.lifecycle.liveData
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.data.repository.JokeRepository
import com.renanparis.chuckjokes.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class RandomJokeUseCase(private val repository: JokeRepository) {

    fun getRandomJoke(category: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getRandomJoke(category)))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }

    fun deleteJoke(joke: Joke) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data =repository.deleteJoke(joke)))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }

    fun saveJoke(joke: Joke) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = repository.saveJoke(joke)))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }
}
