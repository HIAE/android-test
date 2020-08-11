package com.wesleyerick.chuckjokes.interactor


import com.wesleyerick.chuckjokes.entity.Categories
import com.wesleyerick.chuckjokes.entity.MessageDetails
import com.wesleyerick.chuckjokes.router.startPhraseScreen
import com.wesleyerick.chuckjokes.view.MainActivity
import com.wesleyerick.chuckjokes.view.PhraseActivity
import com.wesleyerick.chuckjokes.view.SplashScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Interactor () {

    fun findRandom(){
        var retrofit=  Api.getRetrofit("https://api.chucknorris.io/jokes/")
        val endpoint = retrofit.create(ApiService::class.java)
        val callback = endpoint.findRandomPhrase()

        callback.enqueue(object : Callback<MessageDetails>{
            override fun onFailure(call: Call<MessageDetails>, t: Throwable) {
            }

            override fun onResponse(call: Call<MessageDetails>, response: Response<MessageDetails>) {
               response.body().let {
                   MainActivity().getMessage(it!!.value)
                }


            }

        })
    }

    fun findCategories(){
        var retrofit=  Api.getRetrofit("https://api.chucknorris.io/jokes/")
        val endpoint = retrofit.create(ApiService::class.java)
        val callback = endpoint.findCategories()

        callback.enqueue(object : Callback<ArrayList<String>>{
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
            }

            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                response.body()?.forEach {
                    SplashScreen().addCategoryItem(it)
                }


            }

        })

    }

    fun findCategoryMessage(type : String){
        var retrofit=  Api.getRetrofit("https://api.chucknorris.io/jokes/")
        val endpoint = retrofit.create(ApiService::class.java)
        val callback = endpoint.findCategoryMessage(type)

        callback.enqueue(object : Callback<MessageDetails>{
            override fun onFailure(call: Call<MessageDetails>, t: Throwable) {
            }

            override fun onResponse(call: Call<MessageDetails>, response: Response<MessageDetails>) {
                response.body().let {
                            MainActivity().getMessage(it!!.value)
                }


            }

        })

    }
}