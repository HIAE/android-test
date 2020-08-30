package com.araujoraul.aechuck.domain

import com.araujoraul.aechuck.domain.model.ChuckJokes
import com.araujoraul.aechuck.utils.ConstantsUrls
import retrofit2.Call
import retrofit2.http.GET

interface ChuckService {

    @GET(ConstantsUrls.GET_RANDOM_CHUCK_JOKE)
    fun getRandomChuckJokes() : Call<List<ChuckJokes>>

    @GET(ConstantsUrls.GET_RANDOM_CHUCK_JOKE_BY_CATEGORY)
    fun getRandomChuckJokesByCategory() : Call<List<ChuckJokes>>

    @GET(ConstantsUrls.GET_LIST_AVAILABLE_CATEGORIES)
    fun getCategoriesList() : Call<List<String>>

}