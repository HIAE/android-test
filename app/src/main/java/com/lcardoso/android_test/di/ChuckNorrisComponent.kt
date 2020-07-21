package com.lcardoso.android_test.di

import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.RetrofitProvider
import org.koin.dsl.module

object ChuckNorrisComponent {

    val apiModule = module {
        fun provideRetrofit() =
            RetrofitProvider.providesRetrofitApi(ChuckNorrisAPI::class.java)
        single { provideRetrofit() }
    }
}