package com.example.chuckeinstein.di

import com.example.chuckeinstein.core.Constantes
import com.example.chuckeinstein.data.remoto.conexaoapi.ApiChuckNorris
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constantes.URL_API_CHUCKNORRIS.URL_BASE)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun getApiChuckNorris(retrofit: Retrofit): ApiChuckNorris {
        return retrofit.create(ApiChuckNorris::class.java)
    }
}