package com.renanparis.chuckjokes.data.api

class JokeWebClient(private val jokeApiService: JokeApiService
                    = RetrofitBuilder.jokeApiService) {

    suspend fun getCategories() = jokeApiService.getCategories()

    suspend fun  getRandomJoke(category: String) = jokeApiService.getJoke(category)
}