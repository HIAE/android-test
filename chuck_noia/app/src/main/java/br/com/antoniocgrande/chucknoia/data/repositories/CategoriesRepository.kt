package br.com.antoniocgrande.chucknoia.data.repositories

import br.com.antoniocgrande.chucknoia.data.model.Category
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.data.repositories
 * Created by  : antoniocgrande
 * Date        : 06/05/2020 16:35
 ************************************************************/
interface CategoriesRepository {

    @GET("jokes/categories")
    fun listCategories(): Observable<MutableList<Category>>

}