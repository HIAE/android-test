package com.lcardoso.android_test.api

import com.lcardoso.android_test.data.model.JokeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun fetchCategories(): Single<List<String>>

    @GET("/random")
    fun fetchJokes(
        @Query("category") category: String
    ): Single<JokeResponse>
}