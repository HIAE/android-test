package com.lcardoso.android_test

import android.app.Application
import com.lcardoso.android_test.api.RetrofitProvider
import com.lcardoso.android_test.di.ChuckNorrisComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal open class ChuckNorrisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRetrofit()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ChuckNorrisApplication)
            modules(
                ChuckNorrisComponent.apiModule
            )
        }
    }

    protected open fun initRetrofit() = RetrofitProvider.initRetrofit()
}