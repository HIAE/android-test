package com.araujoraul.aechuck

import android.app.Application
import android.util.Log
import com.araujoraul.aechuck.db.entities.FavoritesEntity

class MainApplication : Application() {

    private val TAG = "MainApplication"

    override fun onCreate() {
        super.onCreate()

        appInstance = this
    }

    companion object {

        var favoriteList = ArrayList<FavoritesEntity>()

        private var appInstance: MainApplication? = null

        fun getInstance(): MainApplication {

            if (appInstance == null) throw  IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "MainApplication.onTerminate()")
    }

}