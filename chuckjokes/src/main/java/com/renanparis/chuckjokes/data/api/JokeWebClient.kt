package com.renanparis.chuckjokes.data.api

class JokeWebClient(private val jokeApiService: JokeApiService
                    = RetrofitBuilder.jokeApiService) {

    fun getJoke(category: String) = jokeApiService.getJoke(category)

    suspend fun getCategories() = jokeApiService.getCategories()
}