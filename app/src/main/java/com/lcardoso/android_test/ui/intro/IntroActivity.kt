package com.lcardoso.android_test.ui.intro

import android.os.Bundle
import android.os.Handler
import com.lcardoso.android_test.BaseActivity
import com.lcardoso.android_test.R
import com.lcardoso.android_test.ui.categories.CategoriesActivity
import org.jetbrains.anko.startActivity

class IntroActivity : BaseActivity(
    layoutId = R.layout.activity_intro
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingIntroScreen()
    }

    private fun loadingIntroScreen() {
        Handler().postDelayed({
            redirectToCategoriesScreen()
        }, SPLASH_TIME)
    }

    private fun redirectToCategoriesScreen() {
        startActivity<CategoriesActivity>()
        finish()
    }

    companion object {
        private const val SPLASH_TIME = 3000L
    }
}