package com.lcardoso.android_test.data

import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.doRequest
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.data.model.JokeResponse
import com.lcardoso.android_test.util.toVO
import io.reactivex.Single

class JokesRepositoryImp(
    private val api: ChuckNorrisAPI
) {

    fun fetchCategories(): Single<CategoriesVO> = doRequest {
        api.fetchCategories().map { categories -> categories.toVO() }
    }

    fun fetchJokes(category: String): Single<JokeResponse> = doRequest {
        api.fetchJokes(category)
    }
}