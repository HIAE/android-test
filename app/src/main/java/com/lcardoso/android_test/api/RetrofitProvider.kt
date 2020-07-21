package com.lcardoso.android_test.api

import com.lcardoso.android_test.util.BASE_URL
import com.lcardoso.android_test.util.TIME_OUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    private lateinit var retrofit: Retrofit

    internal fun <T> providesRetrofitApi(clazz: Class<T>): T = retrofit.create(clazz)

    internal fun initRetrofit() {
        val okHttpClient = providesOkHttpClient()
        retrofit = providesRetrofitClient(okHttpClient)
    }

    private fun providesRetrofitClient(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun providesOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()
}