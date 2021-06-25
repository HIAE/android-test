package com.araujoraul.aechuck.activity

import android.content.Intent
import android.os.Bundle
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.utils.Coroutines
import kotlinx.coroutines.delay

class SplashScreenActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Coroutines.main {
            delay(3_000) // 3 sec
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }
    }

}