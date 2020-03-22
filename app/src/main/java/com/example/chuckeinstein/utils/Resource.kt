package com.example.chuckeinstein.utils

import com.example.chuckeinstein.data.remoto.models.MensagemErro
import com.example.chuckeinstein.utils.Status.*

/**
 *  Pego de https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt
 * */
data class Resource<out T>(val status: Status, val data: T?, val message: MensagemErro?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: MensagemErro, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}