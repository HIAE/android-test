package com.renanparis.chuckjokes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.ui.activity.preferences.SplashScreenPreferences

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        configSharedPreferences()
    }

    private fun configSharedPreferences() {
        val preferences = SplashScreenPreferences(this)
        if (preferences.contains(KEY_PREFERENCES)) {
            delayHalfSecond()
        } else {
            delayTwoSecond()
            preferences.addNotFirstTime(KEY_PREFERENCES)
        }
    }

    private fun delayTwoSecond() {
        Handler().postDelayed({ goToCategoriesJokes() }, 2000)
    }

    private fun delayHalfSecond() {
        Handler().postDelayed({ goToCategoriesJokes() }, 500)
    }

    private fun goToCategoriesJokes() {
        startActivity(Intent(this, CategoriesActivity::class.java))
        finish()
    }

    companion object {
        private const val KEY_PREFERENCES = "first_time_app"
    }

}
