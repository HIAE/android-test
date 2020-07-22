package com.lcardoso.android_test.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lcardoso.android_test.data.model.CategoriesVO

fun <T> LiveData<T>.nomNullObserve(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner, Observer {
        it?.let(observer)
    })
}

fun List<String>.toVO() =
    CategoriesVO(categories = this)