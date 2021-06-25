package com.araujoraul.aechuck.fragments.joke

import androidx.lifecycle.MutableLiveData
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.db.dao.FavoritesDao
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import com.araujoraul.aechuck.domain.ApiService
import com.araujoraul.aechuck.domain.model.ChuckJoke
import com.araujoraul.aechuck.utils.Coroutines
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeRepository(
    private var favoritesDao: FavoritesDao
) {

    private val app = MainApplication.getInstance()
    private val api = ApiService.createInstance(app.applicationContext)

    val favoritedError = MutableLiveData<Event<Boolean>>()
    val favoriteHasSaved = MutableLiveData<Event<Boolean>>()
    val showRandomJoke = MutableLiveData<ChuckJoke>()
    val showProgressBar = MutableLiveData<Boolean>()
    val showMessageNoInternet = MutableLiveData<Event<Boolean>>()
    val showMessageServerError = MutableLiveData<Event<Boolean>>()

    suspend fun saveJokeToFavorites(joke: String, category: String) {

        if (joke.isNotEmpty() && category.isNotEmpty()){

            val favorite = FavoritesEntity()
            favorite.joke = joke
            favorite.category = category

            try {
                favoritesDao.insertFavoriteIntoDatabase(favorite)
                favoriteHasSaved.postValue(Event(true))
            } catch (e: Exception){
                favoritedError.postValue(Event(true))
            }
        }
    }

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
                Coroutines.io {
                    delay(5_000) //5 sec
                    taskRandomJokeByCategory(category)
                }
            }
        })
    }

}