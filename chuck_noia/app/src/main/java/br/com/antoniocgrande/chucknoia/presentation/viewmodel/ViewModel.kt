package br.com.antoniocgrande.chucknoia.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.data.model.Joke
import br.com.antoniocgrande.chucknoia.data.remote.CategoriesService
import br.com.antoniocgrande.chucknoia.data.remote.RetrofitClient
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeState
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presenter.activities
 * Created by  : antoniocgrande
 * Date        : 04/05/2020 18:16
 ************************************************************/
class ViewModel : ViewModel() {


    /**
     *
     * ATTRIBUTES
     *
     */
    private val _state by lazy { MutableLiveData<HomeState>() }
    private val dataService =
        RetrofitClient().get().create(CategoriesService::class.java)


    /**
     *
     * LIVEDATA METHODS
     *
     */
    fun getState() = _state

    fun listCategories() {
        _state.value = HomeState.HideEmptyState
        _state.value = HomeState.ShowLoading
        dataService.listCategoriesService()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Category>> {
                override fun onComplete() {
                    _state.value = HomeState.HideLoading
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Category>) {
                    _state.postValue(HomeState.ListCategoriesSuccess(t))
                }

                override fun onError(e: Throwable) {
                    _state.postValue(HomeState.ListCategoriesFail(e.message))
                    _state.value = HomeState.HideLoading
                }
            })
    }

    fun getRandomJoke(category: Category) {
        _state.value = HomeState.HideEmptyState
        _state.value = HomeState.ShowLoading
        dataService.randomJokeService(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Joke> {
                override fun onComplete() {
                    _state.value = HomeState.HideLoading
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Joke) {
                    _state.postValue(HomeState.RandomJokeSuccess(t))
                }

                override fun onError(e: Throwable) {
                    _state.postValue(HomeState.RandomJokeFail(e.message))
                    _state.value = HomeState.HideLoading
                }
            })
    }

}