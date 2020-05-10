package br.com.antoniocgrande.chucknoia.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.data.remote
 * Created by  : antoniocgrande
 * Date        : 09/05/2020 16:10
 ************************************************************/
class RetrofitClient {


    /**
     *
     * ATTRIBUTES
     *
     */
    private var retrofit: Retrofit? = null
    private val CATEGORIES_API_BASE_URL = "https://api.chucknorris.io/"


    /**
     *
     * METHODS
     *
     */
    fun get(): Retrofit {
        when (retrofit) {
            null -> retrofit = Retrofit.Builder()
                .baseUrl(CATEGORIES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }
}