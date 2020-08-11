package com.wesleyerick.chuckjokes.interactor


import com.wesleyerick.chuckjokes.entity.MessageDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    fun findRandomPhrase() : Call<MessageDetails>

    @GET("categories")
    fun findCategories() : Call<ArrayList<String>>

    @GET("random?")
    fun findCategoryMessage(
        @Query("category") category: String
    ): Call<MessageDetails>

}