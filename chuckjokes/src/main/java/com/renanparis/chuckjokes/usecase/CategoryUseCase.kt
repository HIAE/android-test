package com.renanparis.chuckjokes.usecase

import androidx.lifecycle.liveData
import com.renanparis.chuckjokes.data.repository.JokeRepository
import com.renanparis.chuckjokes.utils.Resource
import kotlinx.coroutines.Dispatchers

class CategoryUseCase(private val repository: JokeRepository) {

    fun getCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }
}