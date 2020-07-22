package com.lcardoso.android_test.data

import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.doRequest
import com.lcardoso.android_test.data.model.CategoriesResponse
import com.lcardoso.android_test.data.model.JokeResponse
import io.reactivex.Single

class JokesRepositoryImp(
    private val api: ChuckNorrisAPI
) {

    fun fetchCategories(): Single<CategoriesResponse> = doRequest {
        api.fetchCategories()
    }

    fun fetchJokes(category: String): Single<JokeResponse> = doRequest {
        api.fetchJokes(category)
    }
}