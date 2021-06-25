package com.araujoraul.aechuck.domain

import com.araujoraul.aechuck.domain.model.ChuckJoke
import com.araujoraul.aechuck.utils.ConstantsUrls
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckService {

    @GET(ConstantsUrls.GET_RANDOM_CHUCK_JOKE)
    fun getRandomJokes() : Call<ChuckJoke>

    @GET(ConstantsUrls.GET_RANDOM_CHUCK_JOKE_BY_CATEGORY)
    fun getRandomJokeByCategory(@Query("category") category: String) : Call<ChuckJoke>

    @GET(ConstantsUrls.GET_LIST_AVAILABLE_CATEGORIES)
    fun getCategoriesList() : Call<List<String>>

}