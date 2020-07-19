package com.renanparis.chuckjokes.usecase

import androidx.lifecycle.liveData
import com.renanparis.chuckjokes.data.repository.JokeRepository
import com.renanparis.chuckjokes.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FavoritesJokeUseCase(private val repository: JokeRepository) {

    fun getFavoritesJoke() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getFavoritesJoke()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }

}
