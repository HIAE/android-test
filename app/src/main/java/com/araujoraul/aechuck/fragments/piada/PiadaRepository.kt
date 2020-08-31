package com.araujoraul.aechuck.fragments.piada

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.domain.ApiService
import com.araujoraul.aechuck.domain.model.ChuckJoke
import com.araujoraul.aechuck.utils.Coroutines
import com.araujoraul.aechuck.utils.Event
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PiadaRepository {

    private val app = MainApplication.getInstance()
    private val api = ApiService.createInstance(app.applicationContext)

    val showRandomJoke = MutableLiveData<ChuckJoke>()
    val showProgressBar = MutableLiveData<Boolean>()
    val showMessageNoInternet = MutableLiveData<Event<Boolean>>()
    val showMessageServerError = MutableLiveData<Event<Boolean>>()

    fun taskRandomJokeByCategory(category: String) {
        Coroutines.io { taskRandomJokeByCategoryBG(category) }
    }

    private fun taskRandomJokeByCategoryBG(category: String) {

        showProgressBar.postValue(true)

        api.getRandomJokeByCategory(category).enqueue(object : Callback<ChuckJoke> {

            override fun onResponse(call: Call<ChuckJoke>, response: Response<ChuckJoke>) {

                when {

                    response.isSuccessful -> {

                        val _response = response.body()

                        if (_response != null){

                            Coroutines.io {
                                delay(1_000)
                                showProgressBar.postValue(false)
                                showMessageNoInternet.postValue(Event(false))
                                showMessageServerError.postValue(Event(false))
                                showRandomJoke.postValue(_response)
                            }
                        }
                    }else -> {
                        showProgressBar.postValue(false)
                        showMessageNoInternet.postValue(Event(false))
                        showMessageServerError.postValue(Event(true))
                    }
                }
            }

            override fun onFailure(call: Call<ChuckJoke>, t: Throwable) {
                showProgressBar.postValue(false)
                showMessageNoInternet.postValue(Event(true))
                showMessageServerError.postValue(Event(false))
                taskRandomJokeByCategory(category)
            }
        })
    }

}