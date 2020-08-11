package com.wesleyerick.chuckjokes.presenter

import android.app.Activity
import android.content.Context
import android.view.View
import com.wesleyerick.chuckjokes.interactor.Interactor
import com.wesleyerick.chuckjokes.router.startPhraseScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

private val interactor : Interactor = Interactor()
private val scope : GlobalScope = GlobalScope

class MainPresenter {
    fun showCategories() {
        interactor.findCategories()
    }

    fun onCategoryClick(type : String) {
        interactor.findCategoryMessage(type)
    }
    fun randomPress(view: Activity, context: Context) {
        view.btnRandom.visibility = View.INVISIBLE
        view.progressBar.visibility = View.VISIBLE

        interactor.findRandom()
    }
}