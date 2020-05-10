package br.com.antoniocgrande.chucknoia.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.antoniocgrande.chucknoia.R


/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presentation.activities
 * Created by  : antoniocgrande
 * Date        : 10/05/2020 17:08
 ************************************************************/
class SplashScreen : AppCompatActivity() {


    /**
     *
     * OVERRIDE METHODS
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            }
        }.start()


        setupView()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupView() {
        setContentView(R.layout.splash_screen)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

}