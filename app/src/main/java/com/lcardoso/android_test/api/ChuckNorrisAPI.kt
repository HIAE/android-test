package com.lcardoso.android_test.api

import com.lcardoso.android_test.data.model.JokeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun fetchCategories(): Single<List<String>>

    @GET("jokes/random")
    fun fetchJokes(
        @Query("category") category: String
    ): Single<JokeResponse>

    @GET("jokes/{joke_id}")
    fun fetchPreviousJokes(
        @Path("joke_id") jokeId: String
    ): Single<JokeResponse>
}