package br.com.antoniocgrande.chucknoia.data.remote

import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.data.model.Joke
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.data.remote
 * Created by  : antoniocgrande
 * Date        : 09/05/2020 17:21
 ************************************************************/
interface CategoriesService {

    @GET("jokes/categories")
    fun listCategoriesService(): Observable<List<Category>>

    @GET("jokes/random")
    fun randomJokeService(@Query("category") category: String): Observable<Joke>

}