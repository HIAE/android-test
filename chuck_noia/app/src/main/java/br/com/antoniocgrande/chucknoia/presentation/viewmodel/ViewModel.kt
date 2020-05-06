package br.com.antoniocgrande.chucknoia.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniocgrande.chucknoia.domain.repositories.CategoriesRepositoryImpl
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers


/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presenter.activities
 * Created by  : antoniocgrande
 * Date        : 04/05/2020 18:16
 ************************************************************/
class ViewModel : ViewModel() {

    private val _stateCategories by lazy { MutableLiveData<HomeState>() }
    private val repository by lazy { CategoriesRepositoryImpl() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getStateCategories() = _stateCategories

    fun listCategories() {
        _stateCategories.value = HomeState.ShowLoading
        compositeDisposable.add(
            repository.listCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<String?>>() {
                    override fun onComplete() {
                        _stateCategories.value = HomeState.HideLoading
                    }

                    override fun onNext(t: List<String?>?) {
                        _stateCategories.postValue(HomeState.ListCategories(t))
                    }

                    override fun onError(e: Throwable?) {
                        _stateCategories.postValue(HomeState.Fail(e?.message))
                    }
                })
        )
    }

}