package com.wesleyerick.chuckjokes.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.wesleyerick.chuckjokes.R
import com.wesleyerick.chuckjokes.entity.Categories
import com.wesleyerick.chuckjokes.view.MainActivity
import com.wesleyerick.chuckjokes.view.PhraseActivity
import com.wesleyerick.chuckjokes.view.PhraseAdapter
import com.wesleyerick.chuckjokes.view.SplashScreen
import kotlinx.android.synthetic.main.activity_main.view.*


fun startPhraseScreen(view: Activity, context: Context, message: String){

    val intent = Intent(context, PhraseActivity::class.java)
    intent.putExtra("PHRASE", message)
    view.startActivity(intent)
}

fun startMainScreen(view: Activity, context: Context, array: ArrayList<String>){

    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra("CATEGORIES", array)
    view.startActivity(intent)
}

