package com.lcardoso.android_test.api

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> doRequest(
    doOnError: (() -> Unit)? = null,
    backendCall: () -> Single<T>
): Single<T> {
    return backendCall()
        .defaultSchedulers()
        .requestIt(doOnError)
}

private fun <T> Single<T>.requestIt(
    doOnError: (() -> Unit)?
): Single<T> {
    return doOnSubscribe { }.doFinally { }.doOnError { doOnError?.invoke() }
}

fun ui(): Scheduler = AndroidSchedulers.mainThread()

fun <T> Single<T>.defaultSchedulers(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(ui())

fun Completable.defaultSchedulers(): Completable =
    subscribeOn(Schedulers.io()).observeOn(ui())