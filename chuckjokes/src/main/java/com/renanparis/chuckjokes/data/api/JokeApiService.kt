package com.renanparis.chuckjokes.data.api

import com.renanparis.chuckjokes.data.model.Joke
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApiService {

    @GET("random")
     fun getJoke(@Query("category") category: String): Joke

    @GET("categories")
    fun getCategories(): List<String>

}
