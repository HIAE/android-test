package com.wesleyerick.chuckjokes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wesleyerick.chuckjokes.R
import com.wesleyerick.chuckjokes.entity.Categories
import com.wesleyerick.chuckjokes.interactor.Api
import com.wesleyerick.chuckjokes.interactor.ApiService
import com.wesleyerick.chuckjokes.presenter.MainPresenter
import com.wesleyerick.chuckjokes.router.startMainScreen
import com.wesleyerick.chuckjokes.router.startPhraseScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity(){

    var presenter: MainPresenter = MainPresenter()

    companion object{
        var array : ArrayList<String> =  ArrayList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        presenter.showCategories()

        GlobalScope.async {
            delay(3000)
            configView()
        }
}

    private fun configView() {
            startMainScreen(this@SplashScreen, this@SplashScreen, array)
    }

    fun addCategoryItem(type : String){
        array.add(0, type)
    }

}
