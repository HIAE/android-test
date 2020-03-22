package com.example.chuckeinstein.utils

import com.example.chuckeinstein.data.remoto.models.MensagemErro

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return Resource.error(MensagemErro(e), null)
        //pode lidar aqui com os tipos de exceção
    }
}