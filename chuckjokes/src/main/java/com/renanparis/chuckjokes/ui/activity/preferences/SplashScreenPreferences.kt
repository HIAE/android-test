package com.renanparis.chuckjokes.ui.activity.preferences

import android.content.Context
import android.content.SharedPreferences

class SplashScreenPreferences(private val context: Context) {

    fun contains(key: String): Boolean {
        val preferences = getSharedPreferences()
        return preferences.contains(key)
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(SPLASH_SCREEN_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun addNotFirstTime(key: String) {
        val preferences = getSharedPreferences()
        val editor = preferences.edit()
        editor.putBoolean(key, true)
        editor.apply()
    }

    companion object {
        private const val SPLASH_SCREEN_PREFERENCES = "com.renanparis.chuckjokes.ui.activity.preferences.SplashScreenPreferences"
    }

}
