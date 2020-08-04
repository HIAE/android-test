package com.renanparis.chuckjokes

import android.app.Application
import com.renanparis.chuckjokes.di.dataModule
import com.renanparis.chuckjokes.di.uiModule
import com.renanparis.chuckjokes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(listOf(
                    uiModule,
                    dataModule,
                    viewModelModule
            ))
        }
    }
}