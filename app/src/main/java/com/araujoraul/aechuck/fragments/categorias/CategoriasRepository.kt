package com.araujoraul.aechuck.fragments.categorias

import androidx.lifecycle.MutableLiveData
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.domain.ApiService
import com.araujoraul.aechuck.utils.Coroutines
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriasRepository {

    private val app = MainApplication.getInstance()
    private val api = ApiService.createInstance(app.applicationContext)

    val showCategories = MutableLiveData<List<String>>()
    val showProgressBar = MutableLiveData<Boolean>()
    val showMessageNoInternet = MutableLiveData<Event<Boolean>>()
    val showMessageServerError = MutableLiveData<Event<Boolean>>()

    fun taskCategories() {
        Coroutines.io { taskCategoriesBG() }
    }

    private fun taskCategoriesBG() {

        showProgressBar.postValue(true)

        api.getCategoriesList().enqueue(object : Callback<List<String>> {

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

                when {

                    response.isSuccessful -> {

                        val _response = response.body()
                        val categorias = mutableListOf<String>()

                        if (_response != null && _response.isNotEmpty())
                            for (categoria in _response)  categorias.add(categoria)

                        Coroutines.io {
                            delay(1_000)
                            showProgressBar.postValue(false)
                            showMessageNoInternet.postValue(Event(false))
                            showMessageServerError.postValue(Event(false))
                            showCategories.postValue(categorias)
                        }
                    }
                    else -> {
                        showProgressBar.postValue(false)
                        showMessageNoInternet.postValue(Event(false))
                        showMessageServerError.postValue(Event(true))
                    }
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                showProgressBar.postValue(false)
                showMessageNoInternet.postValue(Event(true))
                showMessageServerError.postValue(Event(false))
                taskCategories()
            }

        })

    }

}