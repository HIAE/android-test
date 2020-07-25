package com.lcardoso.android_test.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lcardoso.android_test.data.database.JokeEntity
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.data.model.JokeVO

fun <T> LiveData<T>.nomNullObserve(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner, Observer {
        it?.let(observer)
    })
}

fun List<String>.toVO() =
    CategoriesVO(categories = this)

fun View.changeVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun JokeVO.toEntity() = JokeEntity(
    id!!, category!!, joke!!
)